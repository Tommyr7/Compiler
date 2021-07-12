package AST;

import Util.position;

public class Binaryexpr extends Exprnode {
    public Exprnode src1, src2;
    public String op;

    public Binaryexpr(position _pos, Exprnode _src1, Exprnode _src2, String _op) {
        super(_pos);
        src1 = _src1;
        src2 = _src2;
        op = _op;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
