package me.tapeline.quarkj;

import me.tapeline.quarkj.interpretertools.Runtime;
import me.tapeline.quarkj.interpretertools.RuntimeConfig;
import me.tapeline.quarkj.language.types.QType;
import me.tapeline.quarkj.parsingtools.Parser;
import me.tapeline.quarkj.parsingtools.nodes.Node;
import me.tapeline.quarkj.tokenizetools.Lexer;
import me.tapeline.quarkj.tokenizetools.tokens.Token;

import java.util.List;

public class RuntimeWrapper {

    private final boolean DEBUG;
    private final String code;

    public RuntimeWrapper(String code, boolean d) {
        this.code = code;
        this.DEBUG = d;
    }

    public QType run() {
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.lex();
        if (tokens == null) System.exit(100);
        if (DEBUG) for (Token token : tokens) System.out.println(token);

        Parser parser = new Parser(tokens);
        Node codeNode = parser.parseCode();
        if (codeNode == null) System.exit(101);
        if (DEBUG) System.out.println(codeNode);

        Runtime runtime = new Runtime(codeNode, new RuntimeConfig() , code);
        return runtime.run();
    }

}
