package FrontEnd;

import AST.*;
import Util.*;

public class SymbolCollector implements ASTvisitor {
    Scope global, now_scope;

    public SymbolCollector(Scope _global) {
        global = _global;
        global.type_map.put("int", new Literaltype("int"));
        global.type_map.put("bool", new Literaltype("bool"));
        global.type_map.put("string", new Literaltype("string"));
        global.type_map.put("null", new Literaltype("null"));
        global.type_map.put("void", new Literaltype("void"));
        {
            Funcsymbol tmp = new Funcsymbol("print");
            tmp.type = new Literaltype("void");
            tmp.para_list.add(new Varsymbol("str", new Literaltype("string")));
            global.func_map.put("print", tmp);
        }
        {
            Funcsymbol tmp = new Funcsymbol("println");
            tmp.type = new Literaltype("void");
            tmp.para_list.add(new Varsymbol("str", new Literaltype("string")));
            global.func_map.put("println", tmp);
        }
        {
            Funcsymbol tmp = new Funcsymbol("printInt");
            tmp.type = new Literaltype("void");
            tmp.para_list.add(new Varsymbol("n", new Literaltype("int")));
            global.func_map.put("printInt", tmp);
        }
        {
            Funcsymbol tmp = new Funcsymbol("printlnInt");
            tmp.type = new Literaltype("void");
            tmp.para_list.add(new Varsymbol("n", new Literaltype("int")));
            global.func_map.put("printlnInt", tmp);
        }
        {
            Funcsymbol tmp = new Funcsymbol("getString");
            tmp.type = new Literaltype("string");
            global.func_map.put("getString", tmp);
        }
        {
            Funcsymbol tmp = new Funcsymbol("toString");
            tmp.type = new Literaltype("string");
            tmp.para_list.add(new Varsymbol("i", new Literaltype("int")));
            global.func_map.put("toString", tmp);
        }
    }

    @Override
    public void visit(Programnode it) {
        now_scope = global;
        it.body.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(Typenode it) {

    }

    @Override
    public void visit(Classdef it) {
        now_scope = new Scope(now_scope);
        Classtype res = new Classtype(it.id);
        it.varlist.forEach(x -> x.accept(this));
        it.funclist.forEach(x -> x.accept(this));
        if (it.constructor != null) res.constructor = new Funcsymbol(it.constructor.id);
        res.varmap = now_scope.var_map;
        res.funcmap = now_scope.func_map;
        now_scope = now_scope.parent_scope;
        now_scope.new_type(it.id, res, it.pos);
    }

    @Override
    public void visit(Functiondef it) {
        now_scope.new_function(it.id, new Funcsymbol(it.id), it.pos);
    }

    @Override
    public void visit(Partstatement it) {

    }

    @Override
    public void visit(Breakstatement it) {

    }

    @Override
    public void visit(Continuestatement it) {

    }

    @Override
    public void visit(Emptystatement it) {

    }

    @Override
    public void visit(Forstatement it) {

    }

    @Override
    public void visit(Ifstatement it) {

    }

    @Override
    public void visit(Exprstatement it) {

    }

    @Override
    public void visit(Returnstatement it) {

    }

    @Override
    public void visit(Whilestatement it) {

    }

    @Override
    public void visit(Vardefstatement it) {

    }

    @Override
    public void visit(Vardefsubstatement it) {
        now_scope.new_variable(it.id, new Varsymbol(it.id), it.pos);
    }

    @Override
    public void visit(Binaryexpr it) {

    }

    @Override
    public void visit(Boolexpr it) {

    }

    @Override
    public void visit(Funcexpr it) {

    }

    @Override
    public void visit(Intexpr it) {

    }

    @Override
    public void visit(Classexpr it) {

    }

    @Override
    public void visit(Newexpr it) {

    }

    @Override
    public void visit(Nullexpr it) {

    }

    @Override
    public void visit(Stringexpr it) {

    }

    @Override
    public void visit(Thisexpr it) {

    }

    @Override
    public void visit(Varexpr it) {

    }

    @Override
    public void visit(Arrexpr it) {

    }

    @Override
    public void visit(Suffixexpr it) {

    }

    @Override
    public void visit(Prefixexpr it) {

    }

    @Override
    public void visit(Exprlistexpr it) {

    }
}
