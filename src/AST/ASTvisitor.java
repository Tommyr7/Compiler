package AST;

public interface ASTvisitor {
    void visit(Programnode it);

    void visit(Typenode it);

    void visit(Classdef it);

    void visit(Functiondef it);

    void visit(Partstatement it);

    void visit(Breakstatement it);

    void visit(Continuestatement it);

    void visit(Emptystatement it);

    void visit(Forstatement it);

    void visit(Ifstatement it);

    void visit(Exprstatement it);

    void visit(Returnstatement it);

    void visit(Whilestatement it);

    void visit(Vardefstatement it);

    void visit(Vardefsubstatement it);

    void visit(Binaryexpr it);

    void visit(Boolexpr it);

    void visit(Funcexpr it);

    void visit(Intexpr it);

    void visit(Classexpr it);

    void visit(Newexpr it);

    void visit(Nullexpr it);

    void visit(Stringexpr it);

    void visit(Thisexpr it);

    void visit(Varexpr it);

    void visit(Arrexpr it);

    void visit(Suffixexpr it);

    void visit(Prefixexpr it);

    void visit(Exprlistexpr it);
}
