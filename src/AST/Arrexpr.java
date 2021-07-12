package AST;

import Util.position;

public class Arrexpr extends Exprnode {
    public Exprnode base, offset;

    public Arrexpr(position _pos, Exprnode _base, Exprnode _offset) {
        super(_pos, true);
        base = _base;
        offset = _offset;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
