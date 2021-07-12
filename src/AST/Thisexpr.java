package AST;

import Util.position;

public class Thisexpr extends Exprnode {
    public Thisexpr(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
