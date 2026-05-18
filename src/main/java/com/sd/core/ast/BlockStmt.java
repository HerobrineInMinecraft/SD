package com.sd.core.ast;

import java.util.List;

public class BlockStmt extends AstNode {
    public final List<AstNode> stmts;

    public BlockStmt(List<AstNode> stmts) {
        super(stmts.isEmpty() ? null : stmts.get(0).token);
        this.stmts = stmts;
    }
}