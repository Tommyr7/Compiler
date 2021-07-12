package AST;

import Util.position;

public class Nullexpr extends Exprnode {
    public Nullexpr(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
