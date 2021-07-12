package FrontEnd;

import AST.*;
import Util.Classtype;
import Util.Scope;
import Util.Varsymbol;

public class TypeCollector implements ASTvisitor {
    Scope global;
    String class_place;

    public TypeCollector(Scope _global) {
        global = _global;
    }

    @Override
    public void visit(Programnode it) {
        class_place = null;
        it.body.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(Typenode it) {

    }

    @Override
    public void visit(Classdef it) {
        class_place = it.id;
        it.varlist.forEach(x -> x.accept(this));
        it.funclist.forEach(x -> x.accept(this));
        class_place = null;
    }

    @Override
    public void visit(Functiondef it) {
        if (class_place == null) {
            global.func_map.get(it.id).type = global.get_type(it.type);
            it.paralist.forEach(x -> global.func_map.get(it.id).para_list.add(new Varsymbol(x.id, global.get_type(x.type))));
        } else {
            ((Classtype) global.type_map.get(class_place)).funcmap.get(it.id).type = global.get_type(it.type);
            it.paralist.forEach(x -> ((Classtype) global.type_map.get(class_place)).funcmap.get(it.id).para_list.add(new Varsymbol(x.id, global.get_type(x.type))));
        }
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
        if (class_place == null) {
            global.var_map.get(it.id).type = global.get_type(it.type);
        } else {
            ((Classtype) global.type_map.get(class_place)).varmap.get(it.id).type = global.get_type(it.type);
        }
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
