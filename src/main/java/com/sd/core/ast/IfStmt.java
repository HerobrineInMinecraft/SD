package com.sd.core.ast;

public class IfStmt extends AstNode {
    public final AstNode cond;
    public final AstNode thenBranch;
    public final AstNode elseBranch;

    public IfStmt(AstNode cond, AstNode thenBranch, AstNode elseBranch) {
        super(cond.token);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }
}