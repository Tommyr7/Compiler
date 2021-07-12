package AST;

import Util.position;

public class Intexpr extends Exprnode {
    public int value;

    public Intexpr(position _pos, int _value) {
        super(_pos);
        value = _value;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
