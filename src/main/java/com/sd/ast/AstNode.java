package com.sd.ast;

import com.sd.vm.Environment;
import com.sd.vm.RuntimeValue;

public abstract class AstNode {
    public abstract RuntimeValue execute(Environment env);
}