package AST;

import Util.position;

public class Exprstatement extends Statementnode {
    public Exprnode expr;

    public Exprstatement(position _pos, Exprnode _expr) {
        super(_pos);
        expr = _expr;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
