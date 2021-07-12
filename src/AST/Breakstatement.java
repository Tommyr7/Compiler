package AST;

import Util.position;

public class Breakstatement extends Statementnode {
    public Breakstatement(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
