package FrontEnd;

import AST.*;
import Util.*;
import Util.error.semanticError;

import javax.lang.model.type.ArrayType;

public class SemanticChecker implements ASTvisitor {
    public Scope scope, now_scope;
    public Type res_type;
    public Classtype now_class;
    public boolean flag; //whether this part has returned
    public int dep;

    public SemanticChecker(Scope _scope) {
        scope = _scope;
        dep = 0;
    }

    @Override
    public void visit(Programnode it) {
        Funcsymbol func_main = scope.get_function("main", false, it.pos);
        now_scope = scope;
        if (!func_main.type.is_int()) throw new semanticError("The main function must return int!", it.pos);
        if (func_main.para_list.size() > 0)
            throw new semanticError("The main function shouldn't have parameters!", it.pos);
        it.body.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(Typenode it) {

    }

    @Override
    public void visit(Classdef it) {
        now_scope = new Scope(now_scope);
        now_class = (Classtype) scope.type_map.get(it.id);
        now_class.varmap.forEach((key, value) -> now_scope.new_variable(key, value, it.pos));
        now_class.funcmap.forEach((key, value) -> now_scope.new_function(key, value, it.pos));
        it.funclist.forEach(x -> x.accept(this));
        if (it.constructor != null) {
            if (!it.constructor.id.equals(it.id)) throw new semanticError("Wrong constructor!", it.pos);
            it.constructor.accept(this);
        }
        now_scope = now_scope.parent_scope;
        now_class = null;
    }

    @Override
    public void visit(Functiondef it) {
        now_scope = new Scope(now_scope);
        if (it.paralist != null)
            it.paralist.forEach(x -> now_scope.new_variable(x.id, new Varsymbol(x.id, scope.get_type(x.type)), x.pos));
        flag = false;
        if (it.type == null) res_type = new Literaltype("void");
        else res_type = scope.get_type(it.type);
        it.part.accept(this);
        if (it.id.equals("main")) flag = true;
        if (!flag && it.type != null && !it.type.type.equals("void")) throw new semanticError("No return!", it.pos);
        now_scope = now_scope.parent_scope;
    }

    @Override
    public void visit(Partstatement it) {
        it.statelis.forEach(x ->
        {
            if (x instanceof Partstatement) {
                now_scope = new Scope(now_scope);
                x.accept(this);
                now_scope = now_scope.parent_scope;
            } else x.accept(this);
        });
    }

    @Override
    public void visit(Breakstatement it) {
        if (dep == 0) throw new semanticError("Invalid Break!", it.pos);
    }

    @Override
    public void visit(Continuestatement it) {
        if (dep == 0) throw new semanticError("Invalid Break!", it.pos);
    }

    @Override
    public void visit(Emptystatement it) {

    }

    @Override
    public void visit(Forstatement it) {
        if (it.init != null) it.init.accept(this);
        if (it.condition != null) it.condition.accept(this);
        if (it.inc != null) it.inc.accept(this);
        if (it.condition != null && !it.condition.type.is_boolean())
            throw new semanticError("Condition is not boolean!", it.pos);
        now_scope = new Scope(now_scope);
        ++dep;
        it.body.accept(this);
        now_scope = now_scope.parent_scope;
        --dep;
    }

    @Override
    public void visit(Ifstatement it) {
        it.condition.accept(this);
        if (!it.condition.type.is_boolean()) throw new semanticError("Condition is not boolean!", it.pos);
        now_scope = new Scope(now_scope);
        it.truestate.accept(this);
        now_scope = now_scope.parent_scope;
        if (it.falsestate != null) {
            now_scope = new Scope(now_scope);
            it.falsestate.accept(this);
            now_scope = now_scope.parent_scope;
        }
    }

    @Override
    public void visit(Exprstatement it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(Returnstatement it) {
        flag = true;
        if (it.res == null) {
            if (!res_type.is_void()) throw new semanticError("Invalid return!", it.pos);
        } else {
            it.res.accept(this);
            if (!res_type.equal(it.res.type)) throw new semanticError("Invalid return type!", it.pos);
        }
    }

    @Override
    public void visit(Whilestatement it) {
        it.condition.accept(this);
        if (!it.condition.type.is_boolean()) throw new semanticError("Condition is not boolean!", it.pos);
        now_scope = new Scope(now_scope);
        ++dep;
        it.body.accept(this);
        now_scope = now_scope.parent_scope;
        --dep;
    }

    @Override
    public void visit(Vardefstatement it) {
        it.varlist.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(Vardefsubstatement it) {
        Type tmp = scope.get_type(it.type);
        if (tmp.is_void()) throw new semanticError("Invalid variable type!", it.pos);
        if (it.expr != null) {
            it.expr.accept(this);
            if (!tmp.equal(it.expr.type)) throw new semanticError("Invalid initial variable!", it.pos);
        }
        now_scope.new_variable(it.id, new Varsymbol(it.id, tmp), it.pos);
    }

    @Override
    public void visit(Binaryexpr it) {
        it.src1.accept(this);
        it.src2.accept(this);
        if (it.op.equals("*") || it.op.equals("/") || it.op.equals("%") || it.op.equals("-") || it.op.equals("<<") || it.op.equals(">>") || it.op.equals("&") || it.op.equals("|") || it.op.equals("^")) {
            if (it.src1.type.is_int() && it.src2.type.is_int()) it.type = new Literaltype("int");
            else throw new semanticError("Invalid operation type!", it.pos);
        } else if (it.op.equals("+")) {
            if (it.src1.type.is_int() && it.src2.type.is_int()) it.type = new Literaltype("int");
            else if (it.src1.type.is_string() && it.src2.type.is_string()) it.type = new Literaltype("string");
            else throw new semanticError("Invalid operation type!", it.pos);
        } else if (it.op.equals("<") || it.op.equals(">") || it.op.equals("<=") || it.op.equals(">=")) {
            if (it.src1.type.is_int() && it.src2.type.is_int()) it.type = new Literaltype("bool");
            else if (it.src1.type.is_string() && it.src2.type.is_string()) it.type = new Literaltype("bool");
            else throw new semanticError("Invalid operation type!", it.pos);
        } else if (it.op.equals("&&") || it.op.equals("||")) {
            if (it.src1.type.is_boolean() && it.src2.type.is_boolean()) it.type = new Literaltype("bool");
            else throw new semanticError("Invalid operation type!", it.pos);
        } else if (it.op.equals("==") || it.op.equals("!=")) {
            if (it.src1.type.equal(it.src2.type)) it.type = new Literaltype("bool");
            else throw new semanticError("Invalid operation type!", it.pos);
        } else if (it.op.equals("=")) {
            if (!it.src1.type.equal(it.src2.type)) throw new semanticError("Invalid operation type!", it.pos);
            if (!it.src1.assign) throw new semanticError("Invalid assign!", it.pos);
            it.type = it.src1.type;
            it.assign = true;
        } else throw new semanticError("Undefined operation!", it.pos);
    }

    @Override
    public void visit(Boolexpr it) {
        it.type = new Literaltype("bool");
    }

    @Override
    public void visit(Funcexpr it) {
        if (it.name instanceof Varexpr) it.name.type = now_scope.get_function(((Varexpr) it.name).id, true, it.pos);
        else it.name.accept(this); //The function belongs to a class.
        if (!(it.name.type instanceof Funcsymbol)) throw new semanticError("Undefined function!", it.pos);
        Funcsymbol tmp = (Funcsymbol) it.name.type;
        it.exprlist.exprlist.forEach(x -> x.accept(this));
        if (it.exprlist.exprlist.size() != tmp.para_list.size()) throw new semanticError("Wrong parameters!", it.pos);
        for (int i = 0; i < tmp.para_list.size(); i++)
            if (!tmp.para_list.get(i).type.equal(it.exprlist.exprlist.get(i).type))
                throw new semanticError("Wrong parameter type!", it.pos);
        it.type = tmp.type;
    }

    @Override
    public void visit(Intexpr it) {
        it.type = new Literaltype("int");
    }

    @Override
    public void visit(Classexpr it) {
        it.name.accept(this);
        if (it.name.type instanceof ArrayType && it.flag_func && it.id.equals("size")) {
            Funcsymbol tmp = new Funcsymbol("size");
            tmp.type = new Literaltype("int");
            it.type = tmp;
            return;
        }
        if (it.name.type.is_string() && it.flag_func && it.id.equals("length")) {
            Funcsymbol tmp = new Funcsymbol("length");
            tmp.type = new Literaltype("int");
            it.type = tmp;
            return;
        }
        if (it.name.type.is_string() && it.flag_func && it.id.equals("substring")) {
            Funcsymbol tmp = new Funcsymbol("substring");
            tmp.type = new Literaltype("int");
            tmp.para_list.add(new Varsymbol("left", new Literaltype("int")));
            tmp.para_list.add(new Varsymbol("right", new Literaltype("int")));
            it.type = tmp;
            return;
        }
        if (it.name.type.is_string() && it.flag_func && it.id.equals("parseInt")) {
            Funcsymbol tmp = new Funcsymbol("parseInt");
            tmp.type = new Literaltype("int");
            it.type = tmp;
            return;
        }
        if (it.name.type.is_string() && it.flag_func && it.id.equals("ord")) {
            Funcsymbol tmp = new Funcsymbol("ord");
            tmp.type = new Literaltype("int");
            tmp.para_list.add(new Varsymbol("pos", new Literaltype("int")));
            it.type = tmp;
            return;
        }
        if (!(it.name.type instanceof Classtype)) throw new semanticError("Undefined class!", it.pos);
        Classtype tmp = (Classtype) it.name.type;
        if (it.flag_func) {
            if (tmp.funcmap.containsKey(it.id)) it.type = tmp.funcmap.get(it.id);
            else throw new semanticError("Function not found!", it.pos);
        } else {
            if (tmp.varmap.containsKey(it.id)) it.type = tmp.varmap.get(it.id).type;
            else throw new semanticError("Variable not found!", it.pos);
        }
    }

    @Override
    public void visit(Newexpr it) {
        if (it.exprlist != null) {
            it.exprlist.forEach(x ->
            {
                x.accept(this);
                if (!x.type.is_int()) throw new semanticError("Paramater must be int!", it.pos);
            });
        }
        it.type = scope.get_type(it.typenode);
    }

    @Override
    public void visit(Nullexpr it) {
        it.type = new Literaltype("null");
    }

    @Override
    public void visit(Stringexpr it) {
        it.type = new Literaltype("string");
    }

    @Override
    public void visit(Thisexpr it) {
        if (now_class == null) throw new semanticError("Not in class!", it.pos);
        it.type = now_class;
    }

    @Override
    public void visit(Varexpr it) {
        it.type = now_scope.get_variable(it.id, true, it.pos).type;
    }

    @Override
    public void visit(Arrexpr it) {
        it.base.accept(this);
        it.offset.accept(this);
        if (!(it.base.type instanceof Arraytype)) throw new semanticError("Undefined array!", it.pos);
        if (!(it.offset.type.is_int())) throw new semanticError("The parameter must be int!", it.pos);
        Arraytype tmp = (Arraytype) it.base.type;
        if (tmp.dim == 1) it.type = tmp.type;
        else it.type = new Arraytype(tmp.type, tmp.dim - 1);
    }

    @Override
    public void visit(Suffixexpr it) {
        it.src.accept(this);
        if (!it.src.type.is_int()) throw new semanticError("Variable is not int!", it.pos);
        if (!it.src.assign) throw new semanticError("Variable is not assignable!", it.pos);
        it.type = it.src.type;
    }

    @Override
    public void visit(Prefixexpr it) {
        it.src.accept(this);
        if (it.op.equals("++") || it.op.equals("--")) {
            if (!it.src.type.is_int()) throw new semanticError("Variable is not int!", it.pos);
            if (!it.src.assign) throw new semanticError("Variable is not assignable!", it.pos);
            it.assign = true;
            it.type = new Literaltype("int");
        } else if (it.op.equals("+") || it.op.equals("-") || it.op.equals("~")) {
            if (!it.src.type.is_int()) throw new semanticError("Variable is not int!", it.pos);
            it.type = new Literaltype("int");
        } else if (it.op.equals("!")) {
            if (!it.src.type.is_boolean()) throw new semanticError("Variable is not bool!", it.pos);
            it.type = new Literaltype("bool");
        } else throw new semanticError("Undefined operation!", it.pos);
    }

    @Override
    public void visit(Exprlistexpr it) {

    }
}
