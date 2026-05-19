package com.sd.ast.nodes;

import com.sd.ast.AstNode;
import com.sd.vm.Environment;
import com.sd.vm.RuntimeValue;

// 函数定义节点
public class FnStmtNode extends AstNode {
    public final String name;
    public FnStmtNode(String name) {
        this.name = name;
    }
    @Override
    public RuntimeValue execute(Environment env) {
        // 暂不实现函数执行
        return RuntimeValue.nullVal();
    }
}
