package AST;

import Util.position;

public class Emptystatement extends Statementnode {
    public Emptystatement(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
