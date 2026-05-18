package com.sd.core;

import com.sd.core.ast.*;
import java.util.ArrayList;
import java.util.List;

public class SdParser {
    private final List<Token> tokens;
    private int cur = 0;

    public SdParser(List<Token> tokens) { this.tokens = tokens; }

    public List<AstNode> parseProgram() {
        List<AstNode> list = new ArrayList<>();
        while (!isEnd()) list.add(parseStmt());
        return list;
    }

    private AstNode parseStmt() {
        if (match(TokenType.LET)) return parseLetStmt();
        if (match(TokenType.IF)) return parseIfStmt();
        if (match(TokenType.SWITCH)) return parseSwitchStmt();
        if (match(TokenType.MATCH)) return parseMatchStmt();
        if (check(TokenType.LBRACE)) return parseBlock();
        return parseExprStmt();
    }

    private VarModifiers parseModifiers() {
        VarModifiers mods = new VarModifiers();
        while (true) {
            if (match(TokenType.GLOBAL))                mods.isGlobal = true;
            else if (match(TokenType.STATIC))           mods.isStatic = true;
            else if (match(TokenType.READONLY))         mods.isReadonly = true;
            else if (match(TokenType.PUB))              mods.isPub = true;
            else if (match(TokenType.PRIVATE))          mods.isPrivate = true;
            else if (match(TokenType.PROTECTED))        mods.isProtected = true;
            else if (match(TokenType.INTERNAL))         mods.isInternal = true;
            else if (match(TokenType.SEALED))           mods.isSealed = true;
            else if (match(TokenType.OPEN))             mods.isOpen = true;
            else if (match(TokenType.FINAL))            mods.isFinal = true;
            else if (match(TokenType.MUT))              mods.isMut = true;
            else if (match(TokenType.CONST))            mods.isConst = true;
            else if (match(TokenType.LOCAL))            mods.isLocal = true;
            else if (match(TokenType.ABSTRACT))         mods.isAbstract = true;
            else if (match(TokenType.VIRTUAL))          mods.isVirtual = true;
            else if (match(TokenType.OVERRIDE))         mods.isOverride = true;
            else if (match(TokenType.VOLATILE))         mods.isVolatile = true;
            else if (match(TokenType.SYNCHRONIZED))     mods.isSynchronized = true;
            else if (match(TokenType.SYNC))             mods.isSync = true;
            else if (match(TokenType.SEND))             mods.isSend = true;
            else if (match(TokenType.UNSAFE))           mods.isUnsafe = true;
            else break;
        }
        return mods;
    }

    private AstNode parseLetStmt() {
        Token let = prev();
        VarModifiers mods = parseModifiers();
        VarExpr name = new VarExpr(consume(TokenType.IDENTIFIER));

        // ✅ 可选赋值（有 = 才解析，没有就用默认值 0）
        AstNode init;
        if (check(TokenType.ASSIGN)) {  // 这里必须用 check，不能用 match！
            consume(TokenType.ASSIGN);
            init = parseExpr();
        } else {
            // 默认值：完全匹配你的 LiteralExpr 构造器
            init = new LiteralExpr(null, 0);
        }

        consume(TokenType.SEMI);
        return new LetStmt(let, mods, name, init);
    }

    private AstNode parseIfStmt() {
        AstNode cond = parseExpr();
        AstNode then = parseStmt();
        AstNode elseBranch = match(TokenType.ELSE) ? parseStmt() : null;
        return new IfStmt(cond, then, elseBranch);
    }

    private AstNode parseSwitchStmt() {
        AstNode expr = parseExpr();
        consume(TokenType.LBRACE);
        List<CaseStmt> cases = new ArrayList<>();
        while (match(TokenType.CASE)) {
            AstNode val = parseExpr();
            consume(TokenType.COLON);
            cases.add(new CaseStmt(val, parseBlock()));
        }
        AstNode def = null;
        if (match(TokenType.DEFAULT)) {
            consume(TokenType.COLON);
            def = parseBlock();
        }
        consume(TokenType.RBRACE);
        return new SwitchStmt(expr, cases, def);
    }

    private AstNode parseMatchStmt() {
        AstNode expr = parseExpr();
        consume(TokenType.LBRACE);
        List<CaseStmt> branches = new ArrayList<>();
        while (match(TokenType.CASE)) {
            AstNode val = parseExpr();
            consume(TokenType.COLON);
            branches.add(new CaseStmt(val, parseBlock()));
        }
        consume(TokenType.RBRACE);
        return new MatchStmt(expr, branches);
    }

    private BlockStmt parseBlock() {
        consume(TokenType.LBRACE);
        List<AstNode> stmts = new ArrayList<>();
        while (!check(TokenType.RBRACE) && !isEnd()) stmts.add(parseStmt());
        consume(TokenType.RBRACE);
        return new BlockStmt(stmts);
    }

    private AstNode parseExprStmt() {
        AstNode e = parseExpr();
        consume(TokenType.SEMI);
        return new ExprStmt(e);
    }

    private AstNode parseExpr() { return parseComparison(); }

    private AstNode parseComparison() {
        AstNode left = parseAddSub();
        while (match(TokenType.EQ, TokenType.NE, TokenType.LT, TokenType.GT, TokenType.LE, TokenType.GE)) {
            left = new BinaryExpr(prev(), left, parseAddSub());
        }
        return left;
    }

    private AstNode parseAddSub() {
        AstNode left = parseMulDiv();
        while (match(TokenType.PLUS, TokenType.MINUS)) {
            left = new BinaryExpr(prev(), left, parseMulDiv());
        }
        return left;
    }

    private AstNode parseMulDiv() {
        AstNode left = parsePrimary();
        while (match(TokenType.STAR, TokenType.SLASH, TokenType.PERCENT, TokenType.POWER)) {
            left = new BinaryExpr(prev(), left, parsePrimary());
        }
        return left;
    }

    private AstNode parsePrimary() {
        Token t = peek();
        if (match(TokenType.THIS))  return new VarExpr(prev());
        if (match(TokenType.SUPER)) return new VarExpr(prev());
        if (match(TokenType.INTEGER)) return new LiteralExpr(t, Integer.parseInt(t.text));
        if (match(TokenType.FLOAT)) return new LiteralExpr(t, Double.parseDouble(t.text));
        if (match(TokenType.IDENTIFIER)) return new VarExpr(t);
        if (match(TokenType.LPAREN)) {
            AstNode e = parseExpr();
            consume(TokenType.RPAREN);
            return e;
        }
        throw new RuntimeException("语法错误 行" + t.line + "：" + t.type + " '" + t.text + "'");
    }

    private boolean match(TokenType... ts) { for (var t : ts) if (check(t)) { advance(); return true; } return false; }
    private boolean check(TokenType t) { return !isEnd() && peek().type == t; }
    private Token advance() { if (!isEnd()) cur++; return prev(); }
    private Token prev() { return tokens.get(cur - 1); }
    private Token peek() { return tokens.get(cur); }
    private boolean isEnd() { return peek().type == TokenType.EOF; }
    private Token consume(TokenType t) { if (check(t)) return advance(); throw new RuntimeException("期望：" + t); }
}