import ASM.ASMBlockList;
import AST.Programnode;
import FrontEnd.ASTBuilder;
import FrontEnd.SemanticChecker;
import FrontEnd.SymbolCollector;
import FrontEnd.TypeCollector;
import Util.MxErrorListener;
import Util.Scope;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.MxLexer;
import parser.MxParser;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream input = System.in;
        boolean codegen = false;
        if (args.length > 0) {
            for (String arg : args) {
                switch (arg) {
                    case "-semantic" -> codegen = false;
                    case "-codegen" -> codegen = true;
                }
            }
        }
        try {
            Programnode ASTroot;
            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.programs();
            ASTBuilder astBuilder = new ASTBuilder();
            ASTroot = (Programnode) astBuilder.visit(parseTreeRoot);
            Scope global = new Scope(null);
            new SymbolCollector(global).visit(ASTroot);
            new TypeCollector(global).visit(ASTroot);
            global.var_map.clear();
            new SemanticChecker(global).visit(ASTroot);
            if (!codegen) return;
            ASMBlockList root = new ASMBlockList();
            root.print(System.out);
        } catch (Error er) {
            System.err.println(er.toString());
            throw new RuntimeException();
        }
    }
}
