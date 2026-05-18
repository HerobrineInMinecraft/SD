package com.sd.core.ast;

import com.sd.core.Token;

// 变量定义语句
public class LetStmt extends AstNode {
    public final VarModifiers mods;
    public final VarExpr name;
    public final AstNode init;
    public LetStmt(Token letTok, VarModifiers mods, VarExpr name, AstNode init) {
        super(letTok);
        this.mods = mods;
        this.name = name;
        this.init = init;
    }
}

