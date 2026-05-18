package com.sd.core.ast;

import com.sd.core.Token;

public abstract class AstNode {
    public final Token token;
    protected AstNode(Token token) {
        this.token = token;
    }
}