package AST;

import Util.position;

import java.util.ArrayList;

public class Exprlistexpr extends Exprnode {
    public ArrayList<Exprnode> exprlist = new ArrayList<>();

    public Exprlistexpr(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
