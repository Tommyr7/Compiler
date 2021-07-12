package AST;

import Util.position;

public class Whilestatement extends Statementnode {
    public Exprnode condition;
    public Statementnode body;

    public Whilestatement(position _pos, Exprnode _condition, Statementnode _body) {
        super(_pos);
        condition = _condition;
        body = _body;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
