package AST;

import Util.position;

import java.util.ArrayList;

public class Vardefstatement extends Statementnode {
    public ArrayList<Vardefsubstatement> varlist = new ArrayList<>();

    public Vardefstatement(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
