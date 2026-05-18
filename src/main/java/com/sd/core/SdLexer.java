package com.sd.core;

import java.util.ArrayList;
import java.util.List;

public class SdLexer {
    private final String src;
    private int pos = 0;
    private int line = 1;
    private final int len;

    public SdLexer(String src) {
        this.src = src;
        this.len = src.length();
    }

    private char peek() {
        return pos >= len ? '\0' : src.charAt(pos);
    }

    private char peek(int off) {
        return pos + off >= len ? '\0' : src.charAt(pos + off);
    }

    private char next() {
        return pos >= len ? '\0' : src.charAt(pos++);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    public List<Token> scan() {
        List<Token> tokens = new ArrayList<>();
        while (peek() != '\0') {
            char c = peek();
            if (c == '\n') {
                line++;
                next();
                continue;
            }
            if (Character.isWhitespace(c)) {
                next();
                continue;
            }

            if (c == '/' && peek(1) == '/') {
                next();
                next();
                while (peek() != '\0' && peek() != '\n') next();
                continue;
            }

            if (isLetter(c)) {
                int start = pos;
                while (isLetter(peek()) || isDigit(peek())) next();
                String word = src.substring(start, pos);
                tokens.add(new Token(keyword(word), word, line));
                continue;
            }

            if (isDigit(c)) {
                int start = pos;
                while (isDigit(peek())) next();
                if (peek() == '.') {
                    next();
                    while (isDigit(peek())) next();
                    tokens.add(new Token(TokenType.FLOAT, src.substring(start, pos), line));
                } else {
                    tokens.add(new Token(TokenType.INTEGER, src.substring(start, pos), line));
                }
                continue;
            }

            switch (c) {
                case '+' -> { tokens.add(new Token(TokenType.PLUS, "+", line)); next(); }
                case '-' -> { tokens.add(new Token(TokenType.MINUS, "-", line)); next(); }
                case '*' -> {
                    next();
                    if (peek() == '*') {
                        next();
                        tokens.add(new Token(TokenType.POWER, "**", line));
                    } else {
                        tokens.add(new Token(TokenType.STAR, "*", line));
                    }
                }
                case '/' -> { tokens.add(new Token(TokenType.SLASH, "/", line)); next(); }
                case '%' -> { tokens.add(new Token(TokenType.PERCENT, "%", line)); next(); }
                case '=' -> {
                    next();
                    if (peek() == '=') { next(); tokens.add(new Token(TokenType.EQ, "==", line)); }
                    else tokens.add(new Token(TokenType.ASSIGN, "=", line));
                }
                case '!' -> {
                    next();
                    if (peek() == '=') { next(); tokens.add(new Token(TokenType.NE, "!=", line)); }
                    else tokens.add(new Token(TokenType.NOT, "!", line));
                }
                case '<' -> {
                    next();
                    if (peek() == '=') { next(); tokens.add(new Token(TokenType.LE, "<=", line)); }
                    else tokens.add(new Token(TokenType.LT, "<", line));
                }
                case '>' -> {
                    next();
                    if (peek() == '=') { next(); tokens.add(new Token(TokenType.GE, ">=", line)); }
                    else tokens.add(new Token(TokenType.GT, ">", line));
                }
                case ';' -> { tokens.add(new Token(TokenType.SEMI, ";", line)); next(); }
                case '(' -> { tokens.add(new Token(TokenType.LPAREN, "(", line)); next(); }
                case ')' -> { tokens.add(new Token(TokenType.RPAREN, ")", line)); next(); }
                case '{' -> { tokens.add(new Token(TokenType.LBRACE, "{", line)); next(); }
                case '}' -> { tokens.add(new Token(TokenType.RBRACE, "}", line)); next(); }
                case ':' -> { tokens.add(new Token(TokenType.COLON, ":", line)); next(); }
                default -> next();
            }
        }
        tokens.add(new Token(TokenType.EOF, "", line));
        return tokens;
    }

    private TokenType keyword(String s) {
        return switch (s) {
            case "let"          -> TokenType.LET;
            case "if"           -> TokenType.IF;
            case "else"         -> TokenType.ELSE;
            case "switch"       -> TokenType.SWITCH;
            case "case"         -> TokenType.CASE;
            case "default"      -> TokenType.DEFAULT;
            case "match"        -> TokenType.MATCH;
            case "readonly"     -> TokenType.READONLY;
            case "static"       -> TokenType.STATIC;
            case "global"       -> TokenType.GLOBAL;
            case "pub"          -> TokenType.PUB;
            case "private"      -> TokenType.PRIVATE;
            case "protected"    -> TokenType.PROTECTED;
            case "internal"     -> TokenType.INTERNAL;
            case "sealed"       -> TokenType.SEALED;
            case "open"         -> TokenType.OPEN;
            case "final"        -> TokenType.FINAL;
            case "mut"          -> TokenType.MUT;
            case "const"        -> TokenType.CONST;
            case "local"        -> TokenType.LOCAL;
            case "abstract"     -> TokenType.ABSTRACT;
            case "virtual"      -> TokenType.VIRTUAL;
            case "override"     -> TokenType.OVERRIDE;
            case "extends"      -> TokenType.EXTENDS;
            case "implements"   -> TokenType.IMPLEMENTS;
            case "this"         -> TokenType.THIS;
            case "super"        -> TokenType.SUPER;
            case "volatile"     -> TokenType.VOLATILE;
            case "synchronized" -> TokenType.SYNCHRONIZED;
            case "sync"         -> TokenType.SYNC;
            case "send"         -> TokenType.SEND;
            case "unsafe"       -> TokenType.UNSAFE;
            default             -> TokenType.IDENTIFIER;
        };
    }
}