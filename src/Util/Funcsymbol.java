package Util;

import java.util.ArrayList;

public class Funcsymbol extends Type {
    public Type type = null;
    public String id;
    public ArrayList<Varsymbol> para_list = new ArrayList<>();

    public Funcsymbol(String _id) {
        id = _id;
    }
}
