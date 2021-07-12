package AST;

import Util.position;

import java.util.ArrayList;

public class Newexpr extends Exprnode {
    public Typenode typenode;
    public ArrayList<Exprnode> exprlist;

    public Newexpr(position _pos, Typenode _typenode, int _dim, ArrayList<Exprnode> _exprlist) {
        super(_pos);
        typenode = _typenode;
        typenode.dim = _dim;
        exprlist = _exprlist;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
