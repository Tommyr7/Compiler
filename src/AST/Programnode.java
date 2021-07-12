package AST;

import Util.position;

import java.util.ArrayList;

public class Programnode extends ASTnode {
    public ArrayList<ASTnode> body = new ArrayList<>();

    public Programnode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
