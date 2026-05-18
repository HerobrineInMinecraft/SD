package com.sd.core.ast;

import java.util.List;

public class SwitchStmt extends AstNode {
    public final AstNode expr;
    public final List<CaseStmt> cases;
    public final AstNode defaultCase;

    public SwitchStmt(AstNode expr, List<CaseStmt> cases, AstNode defaultCase) {
        super(expr.token);
        this.expr = expr;
        this.cases = cases;
        this.defaultCase = defaultCase;
    }
}