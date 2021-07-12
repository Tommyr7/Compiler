package AST;

import Util.position;

public abstract class ASTnode {
    public position pos;

    public ASTnode(position _pos) {
        pos = _pos;
    }

    public abstract void accept(ASTvisitor visitor);
}