package com.sd;

import com.sd.core.Interpreter;
import com.sd.core.SdLexer;
import com.sd.core.SdParser;
import com.sd.core.Token;
import com.sd.core.ast.AstNode;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));

        String code = """
let volatile a = 1;
let synchronized b = 2;
let sync c = 3;
let send d = 4;
let unsafe e = 5;
            """;

        SdLexer lexer = new SdLexer(code);
        List<Token> tokens = lexer.scan();

        SdParser parser = new SdParser(tokens);
        List<AstNode> program = parser.parseProgram();

        Interpreter interpreter = new Interpreter();
        System.out.println("========== SD 语言运行成功 ==========");
        for (AstNode node : program) {
            interpreter.run(node);
        }
    }
}