package com.sd.core.ast;
public interface AstVisitor<T> {
    T visitLiteral(LiteralExpr expr);
    T visitVar(VarExpr expr);
    T visitBinary(BinaryExpr expr);
    T visitLet(LetStmt stmt);
    T visitExpr(ExprStmt stmt);
    T visitBlock(BlockStmt stmt);
    T visitIf(IfStmt stmt);
    T visitSwitch(SwitchStmt stmt);
    T visitCase(CaseStmt stmt);
    T visitMatch(MatchStmt stmt);
}