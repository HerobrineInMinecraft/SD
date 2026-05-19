package com.sd.ast.nodes;

import com.sd.ast.AstNode;
import com.sd.vm.Environment;
import com.sd.vm.RuntimeValue;

public class LiteralNode extends AstNode {
    public final Object value;

    public LiteralNode(Object value) {
        this.value = value;
    }

    @Override
    public RuntimeValue execute(Environment env) {
        if (value instanceof Integer iv) {
            return RuntimeValue.intVal(iv);
        }
        if (value instanceof Double dv) {
            return RuntimeValue.floatVal(dv);
        }
        if (value instanceof Boolean bv) {
            return RuntimeValue.boolVal(bv);
        }
        if (value instanceof String sv) {
            return RuntimeValue.stringVal(sv);
        }
        return RuntimeValue.nullVal();
    }
}