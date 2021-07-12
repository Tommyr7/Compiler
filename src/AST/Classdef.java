package AST;

import Util.position;

import java.util.ArrayList;

public class Classdef extends Defnode {
    public String id;
    public ArrayList<Vardefsubstatement> varlist = new ArrayList<>();
    public ArrayList<Functiondef> funclist = new ArrayList<>();
    public Functiondef constructor = null;

    public Classdef(position _pos, String _id) {
        super(_pos);
        id = _id;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
