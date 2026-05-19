package com.sd.vm;

import com.sd.ast.AstNode;
import com.sd.ast.nodes.LetStmtNode;
import com.sd.parser.Parser;
import com.sd.lexer.Lexer;
import java.util.List;

public class SDVm {
    private final Environment globalEnv = new Environment();

    public void runSource(String source) {
        Lexer lexer = new Lexer(source);
        Parser parser = new Parser(lexer);
        List<AstNode> program = parser.parseProgram();
        run(program);
    }

    public void run(List<AstNode> program) {
        System.out.println("===== SD 虚拟机开始执行 =====");
        for (AstNode node : program) {
            if (node == null) continue;
            RuntimeValue res = node.execute(globalEnv);

            if (node instanceof LetStmtNode letNode) {
                System.out.printf("变量 %s = %s%n", letNode.name, res);
            }
        }
        System.out.println("===== 执行完成 =====");
    }
}