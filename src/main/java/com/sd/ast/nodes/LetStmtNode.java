package com.sd.ast.nodes;

import com.sd.ast.AstNode;
import com.sd.vm.Environment;
import com.sd.vm.RuntimeValue;

public class LetStmtNode extends AstNode {
    public final String name;
    public final String typeName;
    public final AstNode init;

    public LetStmtNode(String name, String typeName, AstNode init) {
        this.name = name;
        this.typeName = typeName;
        this.init = init;
    }

    @Override
    public RuntimeValue execute(Environment env) {
        if (init == null) {
            RuntimeValue val = RuntimeValue.nullVal();
            env.define(name, val);
            return val;
        }

        RuntimeValue value = init.execute(env);
        env.define(name, value);
        return value;
    }
}