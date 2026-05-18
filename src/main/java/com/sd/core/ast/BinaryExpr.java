package com.sd.core.ast;

import com.sd.core.Token;

// 二元运算表达式
public class BinaryExpr extends AstNode {
    public final AstNode left;
    public final AstNode right;
    public BinaryExpr(Token op, AstNode left, AstNode right) {
        super(op);
        this.left = left;
        this.right = right;
    }
}
