package com.sd.core;

public class Token {
    public final TokenType type;
    public final String text;
    public final int line;

    // 构造函数只有 3 个参数！！！
    public Token(TokenType type, String text, int line) {
        this.type = type;
        this.text = text;
        this.line = line;
    }

    @Override
    public String toString() {
        return "[行" + line + "] " + type + " → '" + text + "'";
    }
}