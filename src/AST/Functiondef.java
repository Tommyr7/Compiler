package AST;

import Util.position;

import java.util.ArrayList;

public class Functiondef extends Defnode {
    public Typenode type;
    public String id;
    public ArrayList<Vardefsubstatement> paralist = new ArrayList<>();
    public Partstatement part;

    public Functiondef(position _pos, Typenode _type, String _id, ArrayList<Vardefsubstatement> _paralist, Partstatement _part) {
        super(_pos);
        type = _type;
        id = _id;
        paralist = _paralist;
        part = _part;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
