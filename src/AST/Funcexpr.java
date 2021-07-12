package AST;

import Util.position;

public class Funcexpr extends Exprnode {
    public Exprnode name;
    public Exprlistexpr exprlist;

    public Funcexpr(position _pos, Exprnode _name, Exprlistexpr _exprlist) {
        super(_pos);
        name = _name;
        exprlist = _exprlist;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
