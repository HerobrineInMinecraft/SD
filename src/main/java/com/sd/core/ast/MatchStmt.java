package com.sd.core.ast;

import java.util.List;

public class MatchStmt extends AstNode {
    public final AstNode expr;
    public final List<CaseStmt> branches;

    public MatchStmt(AstNode expr, List<CaseStmt> branches) {
        super(expr.token);
        this.expr = expr;
        this.branches = branches;
    }
}