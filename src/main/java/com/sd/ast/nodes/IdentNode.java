package com.sd.ast.nodes;

import com.sd.ast.AstNode;
import com.sd.vm.Environment;
import com.sd.vm.RuntimeValue;

// 标识符节点
public class IdentNode extends AstNode {
    public final String name;
    public IdentNode(String name) {
        this.name = name;
    }
    @Override
    public RuntimeValue execute(Environment env) {
        return env.lookup(name); // 从环境查找
    }
}

