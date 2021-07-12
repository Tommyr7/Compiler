package AST;

import Util.Type;
import Util.position;

public abstract class Exprnode extends ASTnode {
    public Type type;
    public boolean assign = false;

    public Exprnode(position _pos) {
        super(_pos);
    }

    public Exprnode(position _pos, boolean _assign) {
        super(_pos);
        assign = _assign;
    }

    public Exprnode(position _pos, Type _type, boolean _assign) {
        super(_pos);
        type = _type;
        assign = _assign;
    }
}
