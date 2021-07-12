package FrontEnd;

import AST.*;
import Util.error.syntaxError;
import Util.position;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.MxBaseVisitor;
import parser.MxParser;

import java.util.ArrayList;

public class ASTBuilder extends MxBaseVisitor<ASTnode> {
    @Override
    public ASTnode visitClassDef(MxParser.ClassDefContext ctx) {
        Classdef res = new Classdef(new position(ctx), ctx.Identifier().getText());
        for (ParserRuleContext i : ctx.varDef()) {
            Vardefstatement tmp = (Vardefstatement) visit(i);
            res.varlist.addAll(tmp.varlist);
        }
        for (ParserRuleContext i : ctx.funcDef()) {
            Functiondef tmp = (Functiondef) visit(i);
            res.funclist.add(tmp);
        }
        if (ctx.classDefstruct().size() != 0) res.constructor = (Functiondef) visit(ctx.classDefstruct().get(0));
        return res;
    }

    @Override
    public ASTnode visitFuncDef(MxParser.FuncDefContext ctx) {
        Functiondef res = new Functiondef(new position(ctx), ctx.returntype() == null ? null : (Typenode) visit(ctx.returntype()), ctx.Identifier().getText(), ctx.paraList() == null ? (new ArrayList<>()) : ((Vardefstatement) visit(ctx.paraList())).varlist, (Partstatement) visit(ctx.part()));
        return res;
    }

    @Override
    public ASTnode visitBinaryexpression(MxParser.BinaryexpressionContext ctx) {
        Exprnode src1 = (Exprnode) visit(ctx.src1), src2 = (Exprnode) visit(ctx.src2);
        String op = ctx.op.getText();
        Binaryexpr res = new Binaryexpr(new position(ctx), src1, src2, op);
        return res;
    }

    @Override
    public ASTnode visitArraycreator(MxParser.ArraycreatorContext ctx) {
        Typenode _type = (Typenode) visit(ctx.basictype());
        ArrayList<Exprnode> _exprlist = new ArrayList<>();
        for (ParserRuleContext x : ctx.expression())
            _exprlist.add((Exprnode) visit(x));
        return new Newexpr(new position(ctx), _type, (ctx.getChildCount() - ctx.expression().size() - 1) / 2, _exprlist);
    }

    @Override
    public ASTnode visitPrograms(MxParser.ProgramsContext ctx) {
        Programnode res = new Programnode(new position(ctx));
        if (ctx.programsimple() != null) {
            for (ParserRuleContext x : ctx.programsimple()) {
                ASTnode tmp = visit(x);
                if (tmp instanceof Vardefstatement) res.body.addAll(((Vardefstatement) tmp).varlist);
                else res.body.add(tmp);
            }
        }
        return res;
    }

    @Override
    public ASTnode visitProgramsimple(MxParser.ProgramsimpleContext ctx) {
        if (ctx.varDef() != null) return visit(ctx.varDef());
        else if (ctx.funcDef() != null) return visit(ctx.funcDef());
        else return visit(ctx.classDef());
    }

    @Override
    public ASTnode visitVarDef(MxParser.VarDefContext ctx) {
        Vardefstatement res = new Vardefstatement(new position(ctx));
        Typenode _type = (Typenode) visit(ctx.type());
        for (ParserRuleContext x : ctx.varDefsimple()) {
            Vardefsubstatement tmp = (Vardefsubstatement) visit(x);
            tmp.type = _type;
            res.varlist.add(tmp);
        }
        return res;
    }

    @Override
    public ASTnode visitVarDefsimple(MxParser.VarDefsimpleContext ctx) {
        return new Vardefsubstatement(new position(ctx), ctx.Identifier().getText(), ctx.expression() == null ? null : (Exprnode) visit(ctx.expression()));
    }

    @Override
    public ASTnode visitParaList(MxParser.ParaListContext ctx) {
        Vardefstatement res = new Vardefstatement(new position(ctx));
        for (ParserRuleContext x : ctx.para()) {
            Vardefsubstatement tmp = (Vardefsubstatement) visit(x);
            res.varlist.add(tmp);
        }
        return res;
    }

    @Override
    public ASTnode visitPara(MxParser.ParaContext ctx) {
        Vardefsubstatement res = new Vardefsubstatement(new position(ctx), ctx.Identifier().getText(), null);
        res.type = (Typenode) visit(ctx.type());
        return res;
    }

    @Override
    public ASTnode visitClassDefstruct(MxParser.ClassDefstructContext ctx) {
        return new Functiondef(new position(ctx), null, ctx.Identifier().getText(), null, (Partstatement) visit(ctx.part()));
    }

    @Override
    public ASTnode visitPartstate(MxParser.PartstateContext ctx) {
        Partstatement res = new Partstatement(new position(ctx));
        if (ctx.part().statement() != null) {
            for (ParserRuleContext x : ctx.part().statement())
                res.statelis.add((Statementnode) visit(x));
        }
        return res;
    }

    @Override
    public ASTnode visitVarDefstate(MxParser.VarDefstateContext ctx) {
        return visit(ctx.varDef());
    }

    @Override
    public ASTnode visitIfstate(MxParser.IfstateContext ctx) {
        return new Ifstatement(new position(ctx), (Exprnode) visit(ctx.expression()), (Statementnode) visit(ctx.st1), (Statementnode) visit(ctx.st2));
    }

    @Override
    public ASTnode visitForstate(MxParser.ForstateContext ctx) {
        return new Forstatement(new position(ctx), ctx.expr1 == null ? null : (Exprnode) visit(ctx.expr1), ctx.expr2 == null ? null : (Exprnode) visit(ctx.expr2), ctx.expr3 == null ? null : (Exprnode) visit(ctx.expr3), (Statementnode) visit(ctx.statement()));
    }

    @Override
    public ASTnode visitWhilestate(MxParser.WhilestateContext ctx) {
        return new Whilestatement(new position(ctx), (Exprnode) visit(ctx.expression()), (Statementnode) visit(ctx.statement()));
    }

    @Override
    public ASTnode visitReturnstate(MxParser.ReturnstateContext ctx) {
        return new Returnstatement(new position(ctx), ctx.expression() == null ? null : (Exprnode) visit(ctx.expression()));
    }

    @Override
    public ASTnode visitContinuestate(MxParser.ContinuestateContext ctx) {
        return new Continuestatement(new position(ctx));
    }

    @Override
    public ASTnode visitBreakstate(MxParser.BreakstateContext ctx) {
        return new Breakstatement(new position(ctx));
    }

    @Override
    public ASTnode visitExprstate(MxParser.ExprstateContext ctx) {
        return new Exprstatement(new position(ctx), (Exprnode) visit(ctx.expression()));
    }

    @Override
    public ASTnode visitNullstate(MxParser.NullstateContext ctx) {
        return new Emptystatement(new position(ctx));
    }

    @Override
    public ASTnode visitPart(MxParser.PartContext ctx) {
        Partstatement res = new Partstatement(new position(ctx));
        if (ctx.statement() != null) {
            for (ParserRuleContext x : ctx.statement())
                res.statelis.add((Statementnode) visit(x));
        }
        return res;
    }

    @Override
    public ASTnode visitErrorcreator(MxParser.ErrorcreatorContext ctx) {
        throw new syntaxError("ErrorCreator", new position(ctx));
    }

    @Override
    public ASTnode visitClasscreator(MxParser.ClasscreatorContext ctx) {
        return new Newexpr(new position(ctx), (Typenode) visit(ctx.basictype()), 0, null);
    }

    @Override
    public ASTnode visitBasiccreator(MxParser.BasiccreatorContext ctx) {
        return new Newexpr(new position(ctx), (Typenode) visit(ctx.basictype()), 0, null);
    }

    @Override
    public ASTnode visitConstexpression(MxParser.ConstexpressionContext ctx) {
        return visit(ctx.constant());
    }

    @Override
    public ASTnode visitNewexpression(MxParser.NewexpressionContext ctx) {
        return visit(ctx.creator());
    }

    @Override
    public ASTnode visitClassexpression(MxParser.ClassexpressionContext ctx) {
        return new Classexpr(new position(ctx), (Exprnode) visit(ctx.expression()), ctx.Identifier().getText());
    }

    @Override
    public ASTnode visitThisexpression(MxParser.ThisexpressionContext ctx) {
        return new Thisexpr(new position(ctx));
    }

    @Override
    public ASTnode visitSubexpression(MxParser.SubexpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTnode visitIdexpression(MxParser.IdexpressionContext ctx) {
        return new Varexpr(new position(ctx), ctx.Identifier().getText());
    }

    @Override
    public ASTnode visitArrexpression(MxParser.ArrexpressionContext ctx) {
        return new Arrexpr(new position(ctx), (Exprnode) visit(ctx.expr1), (Exprnode) visit(ctx.expr2));
    }

    @Override
    public ASTnode visitFuncexpression(MxParser.FuncexpressionContext ctx) {
        Exprnode _expr = (Exprnode) visit(ctx.expression());
        if (_expr instanceof Classexpr) {
            _expr.assign = false;
            ((Classexpr) _expr).flag_func = true;
        }
        return new Funcexpr(new position(ctx), _expr, ctx.expressionList() == null ? new Exprlistexpr(new position(ctx)) : (Exprlistexpr) visit(ctx.expressionList()));
    }

    @Override
    public ASTnode visitExpressionList(MxParser.ExpressionListContext ctx) {
        Exprlistexpr res = new Exprlistexpr(new position(ctx));
        for (ParserRuleContext x : ctx.expression())
            res.exprlist.add((Exprnode) visit(x));
        return res;
    }

    @Override
    public ASTnode visitBasictype(MxParser.BasictypeContext ctx) {
        return new Typenode(new position(ctx), ctx.getText(), 0);
    }

    @Override
    public ASTnode visitType(MxParser.TypeContext ctx) {
        return new Typenode(new position(ctx), ctx.basictype().getText(), (ctx.getChildCount() - 1) / 2);
    }

    @Override
    public ASTnode visitReturntype(MxParser.ReturntypeContext ctx) {
        if (ctx.type() != null) return visit(ctx.type());
        else return new Typenode(new position(ctx), ctx.Void().getText(), 0);
    }

    @Override
    public ASTnode visitSuffixexpression(MxParser.SuffixexpressionContext ctx) {
        return new Suffixexpr(new position(ctx), (Exprnode) visit(ctx.expression()), ctx.op.getText());
    }

    @Override
    public ASTnode visitPrefixexpression(MxParser.PrefixexpressionContext ctx) {
        return new Prefixexpr(new position(ctx), (Exprnode) visit(ctx.expression()), ctx.op.getText());
    }

    @Override
    public ASTnode visitConstant(MxParser.ConstantContext ctx) {
        if (ctx.Integers() != null) return new Intexpr(new position(ctx), Integer.parseInt(ctx.Integers().getText()));
        else if (ctx.Strings() != null) return new Stringexpr(new position(ctx), ctx.Strings().getText());
        else if (ctx.Bools() != null)
            return new Boolexpr(new position(ctx), Boolean.parseBoolean(ctx.Bools().getText()));
        else return new Nullexpr(new position(ctx));
    }
}