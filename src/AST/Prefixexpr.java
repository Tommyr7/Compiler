package AST;

import Util.position;

public class Prefixexpr extends Exprnode {
    public Exprnode src;
    public String op;

    public Prefixexpr(position _pos, Exprnode _src, String _op) {
        super(_pos);
        src = _src;
        op = _op;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
