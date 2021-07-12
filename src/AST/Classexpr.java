package AST;

import Util.position;

public class Classexpr extends Exprnode {
    public Exprnode name;
    public String id;
    public boolean flag_func = false; //to check if it is a function or a variable

    public Classexpr(position _pos, Exprnode _name, String _id) {
        super(_pos, true);
        name = _name;
        id = _id;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
