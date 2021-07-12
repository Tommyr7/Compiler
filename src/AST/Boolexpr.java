package AST;

import Util.position;

public class Boolexpr extends Exprnode {
    public boolean value;

    public Boolexpr(position _pos, boolean _value) {
        super(_pos);
        value = _value;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
