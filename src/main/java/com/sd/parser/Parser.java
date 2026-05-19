package com.sd.parser;

import com.sd.ast.AstNode;
import com.sd.ast.nodes.FnStmtNode;
import com.sd.ast.nodes.IdentNode;
import com.sd.ast.nodes.LetStmtNode;
import com.sd.ast.nodes.LiteralNode;
import com.sd.lexer.Lexer;
import com.sd.lexer.Token;
import com.sd.lexer.TokenType;
import com.sd.module.FileImportContext;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final Lexer lexer;
    private final FileImportContext importContext = new FileImportContext();
    private Token curToken;
    private Token peekToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        nextToken();
        nextToken();
    }

    private void nextToken() {
        curToken = peekToken;
        peekToken = lexer.nextToken();
    }

    private boolean curIs(TokenType type) {
        return curToken.type == type;
    }

    private boolean peekIs(TokenType type) {
        return peekToken.type == type;
    }

    private void eat(TokenType type) {
        if (curIs(type)) {
            nextToken();
        }
    }

    // ==============================
    // 解析整个程序，返回 AST 列表
    // ==============================
    public List<AstNode> parseProgram() {
        List<AstNode> program = new ArrayList<>();
        while (!curIs(TokenType.EOF)) {
            AstNode stmt = parseStatement();
            if (stmt != null) {
                program.add(stmt);
            }
        }
        return program;
    }

    // ==============================
    // 解析单个语句
    // ==============================
    private AstNode parseStatement() {
        switch (curToken.type) {
            case IMPORT:
                parseImportStmt();
                return null;
            case LET:
                return parseLetStmt();
            case FN:
                return parseFnStmt();
            default:
                return parseExpression();
        }
    }

    // ==============================
    // 解析 import 模块
    // ==============================
    private void parseImportStmt() {
        eat(TokenType.IMPORT);
        StringBuilder moduleName = new StringBuilder();

        while (curIs(TokenType.IDENTIFIER) || curIs(TokenType.DOT)) {
            moduleName.append(curToken.literal);
            nextToken();
        }

        importContext.importModule(moduleName.toString());
        eat(TokenType.SEMICOLON);
    }

    // ==============================
    // 解析变量声明 let a:i32 = 100;
    // ==============================
    private LetStmtNode parseLetStmt() {
        eat(TokenType.LET);
        String varName = curToken.literal;
        eat(TokenType.IDENTIFIER);

        String typeName = null;
        if (curIs(TokenType.COLON)) {
            eat(TokenType.COLON);
            String rawType = curToken.literal;
            eat(TokenType.IDENTIFIER);

            if (importContext.isContextIdentifier(rawType)) {
                typeName = rawType;
            } else {
                System.err.println("【类型错误】未知类型: " + rawType + " (请先导入模块)");
            }
        }

        AstNode init = null;
        if (curIs(TokenType.EQ)) {
            eat(TokenType.EQ);
            init = parseExpression();
        }
        eat(TokenType.SEMICOLON);

        return new LetStmtNode(varName, typeName, init);
    }

    // ==============================
    // 解析函数 fn test() {}
    // ==============================
    private FnStmtNode parseFnStmt() {
        eat(TokenType.FN);
        String fnName = curToken.literal;
        eat(TokenType.IDENTIFIER);

        eat(TokenType.L_PAREN);
        eat(TokenType.R_PAREN);
        eat(TokenType.L_BRACE);
        eat(TokenType.R_BRACE);

        return new FnStmtNode(fnName);
    }

    // ==============================
    // 解析表达式
    // ==============================
    private AstNode parseExpression() {
        Token token = curToken;  // 先拿当前token
        switch (token.type) {
            case IDENTIFIER:
                nextToken();
                return new IdentNode(token.literal);

            case INT_LIT:
                // 👇 先解析，再nextToken！
                int num = Integer.parseInt(token.literal);
                nextToken();
                return new LiteralNode(num);

            case FLOAT_LIT:
                // 👇 先解析，再nextToken！
                double f = Double.parseDouble(token.literal);
                nextToken();
                return new LiteralNode(f);

            case STRING_LIT:
                nextToken();
                return new LiteralNode(token.literal);

            case TRUE:
            case FALSE:
                nextToken();
                return new LiteralNode(token.type == TokenType.TRUE);

            default:
                nextToken();
                return null;
        }
    }
}