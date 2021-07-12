package AST;

import Util.position;

public class Returnstatement extends Statementnode {
    public Exprnode res;

    public Returnstatement(position _pos, Exprnode _res) {
        super(_pos);
        res = _res;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
