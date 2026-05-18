package com.sd.core.ast;

// 表达式语句
public class ExprStmt extends AstNode {
    public final AstNode expr;
    public ExprStmt(AstNode expr) {
        super(expr.token);
        this.expr = expr;
    }
}
