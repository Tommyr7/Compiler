package AST;

import Util.position;

public class Ifstatement extends Statementnode {
    public Exprnode condition;
    public Statementnode truestate, falsestate;

    public Ifstatement(position _pos, Exprnode _condition, Statementnode _truestate, Statementnode _falsestate) {
        super(_pos);
        condition = _condition;
        truestate = _truestate;
        falsestate = _falsestate;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
