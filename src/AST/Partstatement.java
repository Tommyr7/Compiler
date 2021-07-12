package AST;

import Util.position;

import java.util.ArrayList;

public class Partstatement extends Statementnode {
    public ArrayList<Statementnode> statelis = new ArrayList<>();

    public Partstatement(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
