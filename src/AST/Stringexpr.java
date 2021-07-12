package AST;

import Util.position;

public class Stringexpr extends Exprnode {
    public String value;

    public Stringexpr(position _pos, String _value) {
        super(_pos);
        value = _value;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
