package com.sd.core.ast;

import com.sd.core.Token;

// 字面量表达式
public class LiteralExpr extends AstNode {
    public final Object value;
    public LiteralExpr(Token token, Object value) {
        super(token);
        this.value = value;
    }
}

