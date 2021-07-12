package AST;

import Util.position;

public class Continuestatement extends Statementnode {
    public Continuestatement(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
