package com.sd.core.ast;

public class CaseStmt extends AstNode {
    public final AstNode value;
    public final AstNode body;

    public CaseStmt(AstNode value, AstNode body) {
        super(value.token);
        this.value = value;
        this.body = body;
    }
}