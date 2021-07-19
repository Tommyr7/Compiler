package BackEnd;

import ASM.ASMBlock;
import ASM.ASMBlockList;
import ASM.inst.*;
import ASM.operand.*;
import AST.*;
import Util.Arraytype;
import Util.Classtype;
import Util.Funcsymbol;

import java.util.ArrayList;

public class ASMBuilder implements ASTvisitor {
    ASMBlockList root;
    ASMBlock now_block;
    public String loop_break, loop_continue; // for loops
    public boolean flag_main = false; // if it is in the main function
    public ArrayList<Vardefsubstatement> def_var = new ArrayList<>();
    int label = 0;

    public ASMBuilder(ASMBlockList _root) {
        root = _root;
    }

    @Override
    public void visit(Programnode it) {
        it.body.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(Typenode it) {

    }

    @Override
    public void visit(Classdef it) {
        it.funclist.forEach(x -> x.accept(this));
        if (it.constructor != null) it.constructor.accept(this);
    }

    @Override
    public void visit(Functiondef it) {
        now_block = new ASMBlock(it.funcsymbol.id2);
        root.blocks.add(now_block);
        if (it.funcsymbol.flag_class) {
            if (it.id.equals("Main")) {
                flag_main = true;
                def_var.forEach(x -> x.accept(this));
            }
            for (int i = 0; i < it.paralist.size(); i++) {
                it.paralist.get(i).varsymbol.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Move(new Preg("a" + i), it.paralist.get(i).varsymbol.vreg_id));
            }
            it.part.accept(this);
            if (it.id.equals("main") && !it.flag_return) {
                // main() must have return value zero
                now_block.inst.add(new Move(new Preg("zero"), new Preg("a0")));
                now_block.inst.add(new Jump("Return_" + now_block.id));
            }
        } else {
            now_block.inst.add(new Move(new Preg("a0"), new Vreg(++now_block.cnt))); // this pointer is always Vreg(2)
            for (int i = 0; i < it.paralist.size(); i++) {
                it.paralist.get(i).varsymbol.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Move(new Preg("a" + (i + 1)), it.paralist.get(i).varsymbol.vreg_id));
            }
            it.part.accept(this);
        }
    }

    @Override
    public void visit(Partstatement it) {
        it.statelis.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(Breakstatement it) {
        now_block.inst.add(new Jump(loop_break));
    }

    @Override
    public void visit(Continuestatement it) {
        now_block.inst.add(new Jump(loop_continue));
    }

    @Override
    public void visit(Emptystatement it) {

    }

    @Override
    public void visit(Forstatement it) {
        ++label;
        if (it.init != null) it.init.accept(this);
        String tmp_loop_break = loop_break, tmp_loop_continue = loop_continue;
        String loop_condition = "loop_condition" + label, loop_inc = "loop_inc" + label, loop_end = "loop_end" + label;
        loop_break = loop_end;
        loop_continue = it.inc == null ? loop_condition : loop_inc;
        now_block.inst.add(new Label(loop_condition));
        if (it.condition != null) {
            it.condition.accept(this);
            now_block.inst.add(new Branch(it.condition.vreg_id, null, loop_end, "beqz"));
        }
        it.body.accept(this);
        if (it.inc != null) {
            now_block.inst.add(new Label(loop_inc));
            it.inc.accept(this);
        }
        now_block.inst.add(new Jump(loop_condition));
        now_block.inst.add(new Label(loop_end));
        loop_break = tmp_loop_break;
        loop_continue = tmp_loop_continue;
    }

    @Override
    public void visit(Ifstatement it) {
        ++label;
        it.condition.accept(this);
        String if_true = "if_true" + label, if_end = "if_end" + label, if_false = it.falsestate == null ? if_end : "if_false" + label;
        now_block.inst.add(new Branch(it.condition.vreg_id, null, if_false, "beqz"));
        now_block.inst.add(new Label(if_true));
        it.truestate.accept(this);
        if (it.falsestate == null) {
            now_block.inst.add(new Label(if_end));
        } else {
            now_block.inst.add(new Jump(if_end));
            now_block.inst.add(new Label(if_false));
            it.falsestate.accept(this);
            now_block.inst.add(new Label(if_end));
        }
    }

    @Override
    public void visit(Exprstatement it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(Returnstatement it) {
        if (it.res != null) {
            it.res.accept(this);
            now_block.inst.add(new Move(it.res.vreg_id, new Preg("a0")));
        }
        now_block.inst.add(new Jump("Return_" + now_block.id));
    }

    @Override
    public void visit(Whilestatement it) {
        ++label;
        String tmp_loop_break = loop_break, tmp_loop_continue = loop_continue;
        String loop_condition = "loop_condition" + label, loop_end = "loop_end" + label;
        loop_break = loop_end;
        loop_continue = loop_condition;
        now_block.inst.add(new Label(loop_condition));
        it.condition.accept(this);
        now_block.inst.add(new Branch(it.condition.vreg_id, null, loop_end, "beqz"));
        it.body.accept(this);
        now_block.inst.add(new Jump(loop_condition));
        now_block.inst.add(new Label(loop_end));
        loop_break = tmp_loop_break;
        loop_continue = tmp_loop_continue;
    }

    @Override
    public void visit(Vardefstatement it) {
        it.varlist.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(Vardefsubstatement it) {
        if (it.varsymbol.is_global) {
            if (!flag_main) {
                // the instruction is outside any function, need to do in main()
                it.varsymbol.vreg_id = new Symbol(it.id);
                root.vars.add(it.id);
                def_var.add(it);
            } else if (it.expr != null) {
                it.expr.accept(this);
                now_block.inst.add(new Move(it.expr.vreg_id, it.varsymbol.vreg_id));
            }
        } else {
            it.varsymbol.vreg_id = new Vreg(++now_block.cnt);
            if (it.expr != null) {
                it.expr.accept(this);
                now_block.inst.add(new Move(it.expr.vreg_id, it.varsymbol.vreg_id));
            }
        }
    }

    @Override
    public void visit(Binaryexpr it) {
        if (it.op.equals("=")) {
            it.src1.accept(this);
            it.src2.accept(this);
            now_block.inst.add(new Move(it.src2.vreg_id, it.src1.vreg_id));
            it.vreg_id = it.src1.vreg_id;
        } else if (it.op.equals("&&")) {
            ++label;
            String true_statement = "true_statement" + label, false_statement = "false_statement" + label, end_statement = "end_statement" + label;
            it.src1.accept(this);
            now_block.inst.add(new Branch(it.src1.vreg_id, null, false_statement, "beqz"));
            it.src2.accept(this);
            now_block.inst.add(new Branch(it.src2.vreg_id, null, false_statement, "beqz"));
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Li(it.vreg_id, new Immediate(1)));
            now_block.inst.add(new Jump(end_statement));
            now_block.inst.add(new Label(false_statement));
            now_block.inst.add(new Li(it.vreg_id, new Immediate(0)));
            now_block.inst.add(new Label(end_statement));
        } else if (it.op.equals("||")) {
            ++label;
            String true_statement = "true_statement" + label, false_statement = "false_statement" + label, end_statement = "end_statement" + label;
            it.src1.accept(this);
            now_block.inst.add(new Branch(it.src1.vreg_id, null, true_statement, "bnez"));
            it.src2.accept(this);
            now_block.inst.add(new Branch(it.src2.vreg_id, null, true_statement, "bnez"));
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Li(it.vreg_id, new Immediate(0)));
            now_block.inst.add(new Jump(end_statement));
            now_block.inst.add(new Label(true_statement));
            now_block.inst.add(new Li(it.vreg_id, new Immediate(1)));
            now_block.inst.add(new Label(end_statement));
        } else if (it.op.equals("*")) {
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "mul"));
        } else if (it.op.equals("/")) {
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "div"));
        } else if (it.op.equals("%")) {
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "rem"));
        } else if (it.op.equals("+")) {
            if (it.src1.type.is_string()) {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Move(it.src1.vreg_id, new Preg("a0")));
                now_block.inst.add(new Move(it.src2.vreg_id, new Preg("a1")));
                now_block.inst.add(new FuncCall("__std_str_add"));
                now_block.inst.add(new Move(new Preg("a0"), it.vreg_id));
            } else {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "add"));
            }
        } else if (it.op.equals("-")) {
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "sub"));
        } else if (it.op.equals("<<")) {
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "sll"));
        } else if (it.op.equals(">>")) {
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "sra"));
        } else if (it.op.equals("<")) {
            if (it.src1.type.is_string()) {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Move(it.src1.vreg_id, new Preg("a0")));
                now_block.inst.add(new Move(it.src2.vreg_id, new Preg("a1")));
                now_block.inst.add(new FuncCall("__std_str_less"));
                now_block.inst.add(new Move(new Preg("a0"), it.vreg_id));
            } else {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "slt"));
            }
        } else if (it.op.equals(">")) {
            if (it.src1.type.is_string()) {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Move(it.src1.vreg_id, new Preg("a0")));
                now_block.inst.add(new Move(it.src2.vreg_id, new Preg("a1")));
                now_block.inst.add(new FuncCall("__std_str_greater"));
                now_block.inst.add(new Move(new Preg("a0"), it.vreg_id));
            } else {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "sgt"));
            }
        } else if (it.op.equals("<=")) {
            if (it.src1.type.is_string()) {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Move(it.src1.vreg_id, new Preg("a0")));
                now_block.inst.add(new Move(it.src2.vreg_id, new Preg("a1")));
                now_block.inst.add(new FuncCall("__std_str_lessequal"));
                now_block.inst.add(new Move(new Preg("a0"), it.vreg_id));
            } else {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "sgt"));
                now_block.inst.add(new Calculation(it.vreg_id, it.vreg_id, new Immediate(1), "xori"));
            }
        } else if (it.op.equals(">=")) {
            if (it.src1.type.is_string()) {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Move(it.src1.vreg_id, new Preg("a0")));
                now_block.inst.add(new Move(it.src2.vreg_id, new Preg("a1")));
                now_block.inst.add(new FuncCall("__std_str_greaterequal"));
                now_block.inst.add(new Move(new Preg("a0"), it.vreg_id));
            } else {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "slt"));
                now_block.inst.add(new Calculation(it.vreg_id, it.vreg_id, new Immediate(1), "xori"));
            }
        } else if (it.op.equals("==")) {
            if (it.src1.type.is_string()) {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Move(it.src1.vreg_id, new Preg("a0")));
                now_block.inst.add(new Move(it.src2.vreg_id, new Preg("a1")));
                now_block.inst.add(new FuncCall("__std_str_equal"));
                now_block.inst.add(new Move(new Preg("a0"), it.vreg_id));
            } else {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "xor"));
                now_block.inst.add(new Calculation(it.vreg_id, it.vreg_id, null, "seqz"));
            }
        } else if (it.op.equals("!=")) {
            if (it.src1.type.is_string()) {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Move(it.src1.vreg_id, new Preg("a0")));
                now_block.inst.add(new Move(it.src2.vreg_id, new Preg("a1")));
                now_block.inst.add(new FuncCall("__std_str_notequal"));
                now_block.inst.add(new Move(new Preg("a0"), it.vreg_id));
            } else {
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "xor"));
                now_block.inst.add(new Calculation(it.vreg_id, it.vreg_id, null, "snez"));
            }
        } else if (it.op.equals("&")) {
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "and"));
        } else if (it.op.equals("^")) {
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "xor"));
        } else if (it.op.equals("|")) {
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(it.vreg_id, it.src1.vreg_id, it.src2.vreg_id, "or"));
        }
    }

    @Override
    public void visit(Boolexpr it) {
        it.vreg_id = new Vreg(++now_block.cnt);
        now_block.inst.add(new Li(it.vreg_id, new Immediate(it.value ? 1 : 0)));
    }

    @Override
    public void visit(Funcexpr it) {
        it.exprlist.exprlist.forEach(x -> x.accept(this));
        if (it.name instanceof Classexpr) {
            // it is a function in class and be called outside the class
            it.name.accept(this);
            if ((((Classexpr) it.name).name.type instanceof Arraytype) && (((Funcsymbol) it.name.type).id.equals("size"))) {
                // check if the function is array.size()
                it.vreg_id = new Vreg(++now_block.cnt);
                now_block.inst.add(new Load(it.vreg_id, it.name.vreg_id));
            }
            now_block.inst.add(new Move(it.name.vreg_id, new Preg("a0")));
            for (int i = 0; i < it.exprlist.exprlist.size(); i++)
                now_block.inst.add(new Move(it.exprlist.exprlist.get(i).vreg_id, new Preg("a" + (i + 1))));
        } else if (((Funcsymbol) it.name.type).flag_class) {
            // it is a function in class and be called in the class
            now_block.inst.add(new Move(new Vreg(2), new Preg("a0")));
            for (int i = 0; i < it.exprlist.exprlist.size(); i++)
                now_block.inst.add(new Move(it.exprlist.exprlist.get(i).vreg_id, new Preg("a" + (i + 1))));
        } else {
            // it is a function which does not belong to any class
            for (int i = 0; i < it.exprlist.exprlist.size(); i++)
                now_block.inst.add(new Move(it.exprlist.exprlist.get(i).vreg_id, new Preg("a" + i)));
        }
        now_block.inst.add(new FuncCall(((Funcsymbol) it.name.type).id2));
        //return value
        it.vreg_id = new Vreg(++now_block.cnt);
        now_block.inst.add(new Move(new Preg("a0"), it.vreg_id));
    }

    @Override
    public void visit(Intexpr it) {
        it.vreg_id = new Vreg(++now_block.cnt);
        now_block.inst.add(new Li(it.vreg_id, new Immediate(it.value)));
    }

    @Override
    public void visit(Classexpr it) {
        it.name.accept(this);
        if (it.flag_func) it.vreg_id = it.name.vreg_id;
        else {
            Vreg tmp = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(tmp, it.name.vreg_id, new Immediate(((Immediate) it.varsymbol.vreg_id).val * 4), "addi"));
            it.vreg_id = tmp;
        }
    }

    public Operand new_array(int id, Newexpr it) {
        Vreg res = new Vreg(++now_block.cnt);
        Operand sz = it.exprlist.get(id).vreg_id;
        now_block.inst.add(new Calculation(new Preg("a0"), sz, new Immediate(1), "addi"));
        now_block.inst.add(new Calculation(new Preg("a0"), new Preg("a0"), new Immediate(2), "slli"));
        now_block.inst.add(new FuncCall("__std_malloc"));
        now_block.inst.add(new Move(new Preg("a0"), res));
        now_block.inst.add(new Move(sz, new Address(res)));
        if (id < it.exprlist.size() - 1) {
            // we still need to call new_array for the next dimension
            // it is a loop here
            Operand i = new Vreg(++now_block.cnt);
            now_block.inst.add(new Move(sz, i)); // i=sz
            ++label;
            String loop_condition = "new_loop_condition" + label, loop_end = "new_loop_end" + label;
            now_block.inst.add(new Label(loop_condition));
            now_block.inst.add(new Branch(i, null, loop_end, "beqz")); // i!=0
            Vreg tmp = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(tmp, i, new Immediate(2), "slli"));
            now_block.inst.add(new Calculation(tmp, tmp, res, "add"));
            now_block.inst.add(new Move(new_array(id + 1, it), new Address(tmp)));
            now_block.inst.add(new Calculation(i, i, new Immediate(-1), "addi")); // --i
            now_block.inst.add(new Jump(loop_condition));
            now_block.inst.add(new Label(loop_end));
        }
        return res;
    }

    @Override
    public void visit(Newexpr it) {
        if (it.exprlist != null) it.exprlist.forEach(x -> x.accept(this));
        if (it.type instanceof Arraytype) it.vreg_id = new_array(0, it);
        else {
            now_block.inst.add(new Li(new Preg("a0"), new Immediate(((Classtype) it.type).size())));
            now_block.inst.add(new FuncCall("__std_malloc"));
            it.vreg_id = new Vreg(++now_block.cnt);
            now_block.inst.add(new Move(new Preg("a0"), it.vreg_id));
            if (((Classtype) it.type).constructor != null) {
                now_block.inst.add(new FuncCall(((Classtype) it.type).constructor.id2));
            }
        }
    }

    @Override
    public void visit(Nullexpr it) {
        it.vreg_id = new Preg("zero");
    }

    @Override
    public void visit(Stringexpr it) {
        root.strings.add(it.value);
        it.vreg_id = new Vreg(++now_block.cnt);
        now_block.inst.add(new Li(it.vreg_id, new Symbol("Const String" + String.valueOf(root.strings.size()))));
    }

    @Override
    public void visit(Thisexpr it) {
        it.vreg_id = new Vreg(2);
    }

    @Override
    public void visit(Varexpr it) {
        if (it.varsymbol.flag_class) {
            Vreg tmp = new Vreg(++now_block.cnt);
            now_block.inst.add(new Calculation(tmp, new Vreg(2), new Immediate(((Immediate) it.varsymbol.vreg_id).val * 4), "addi"));
        } else it.vreg_id = it.varsymbol.vreg_id;
    }

    @Override
    public void visit(Arrexpr it) {
        it.base.accept(this);
        it.offset.accept(this);
        it.vreg_id = new Vreg(++now_block.cnt);
        now_block.inst.add(new Calculation(it.vreg_id, it.offset.vreg_id, new Immediate(1), "addi"));
        now_block.inst.add(new Calculation(it.vreg_id, it.vreg_id, new Immediate(2), "slli"));
        now_block.inst.add(new Calculation(it.vreg_id, it.vreg_id, it.base.vreg_id, "add"));
    }

    @Override
    public void visit(Suffixexpr it) {
        it.src.accept(this);
        it.vreg_id = new Vreg(++now_block.cnt);
        now_block.inst.add(new Move(it.src.vreg_id, it.vreg_id));
        if (it.op.equals("++")) {
            now_block.inst.add(new Calculation(it.src.vreg_id, it.src.vreg_id, new Immediate(1), "addi"));
        } else if (it.op.equals("--")) {
            now_block.inst.add(new Calculation(it.src.vreg_id, it.src.vreg_id, new Immediate(-1), "addi"));
        }
    }

    @Override
    public void visit(Prefixexpr it) {
        it.src.accept(this);
        if (it.op.equals("++") || it.op.equals("--") || it.op.equals("+")) {
            it.vreg_id = it.src.vreg_id;
            if (it.op.equals("++")) {
                now_block.inst.add(new Calculation(it.vreg_id, it.vreg_id, new Immediate(1), "addi"));
            } else if (it.op.equals("--")) {
                now_block.inst.add(new Calculation(it.vreg_id, it.vreg_id, new Immediate(-1), "addi"));
            }
        } else {
            it.vreg_id = new Vreg(++now_block.cnt);
            if (it.op.equals("-")) {
                now_block.inst.add(new Calculation(it.vreg_id, it.src.vreg_id, null, "neg"));
            } else if (it.op.equals("~")) {
                now_block.inst.add(new Calculation(it.vreg_id, it.src.vreg_id, null, "not"));
            } else if (it.op.equals("!")) {
                now_block.inst.add(new Calculation(it.vreg_id, it.src.vreg_id, null, "seqz"));
            }
        }
    }

    @Override
    public void visit(Exprlistexpr it) {

    }
}
