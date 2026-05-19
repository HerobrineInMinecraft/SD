package com.sd.lexer;

public class Token {
    public final TokenType type;
    public final String literal;
    public final int line;
    public final int col;

    public Token(TokenType type, String literal, int line, int col) {
        this.type = type;
        this.literal = literal;
        this.line = line;
        this.col = col;
    }

    @Override
    public String toString() {
        return String.format("[%d:%d] %-15s %s", line, col, type, literal);
    }
}