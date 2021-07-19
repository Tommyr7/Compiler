package AST;

import Util.Varsymbol;
import Util.position;

public class Vardefsubstatement extends Statementnode {
    public Typenode type;
    public String id;
    public Exprnode expr;
    public Varsymbol varsymbol;

    public Vardefsubstatement(position _pos, String _id, Exprnode _expr) {
        super(_pos);
        id = _id;
        expr = _expr;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
