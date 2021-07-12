package AST;

import Util.position;

public class Forstatement extends Statementnode {
    public Exprnode init, condition, inc;
    public Statementnode body;

    public Forstatement(position _pos, Exprnode _init, Exprnode _condition, Exprnode _inc, Statementnode _body) {
        super(_pos);
        init = _init;
        condition = _condition;
        inc = _inc;
        body = _body;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }

}
