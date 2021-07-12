package AST;

import Util.position;

public class Varexpr extends Exprnode {
    public String id;

    public Varexpr(position _pos, String _id) {
        super(_pos, true);
        id = _id;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
