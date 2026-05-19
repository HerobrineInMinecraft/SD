package com.sd.vm;

import java.util.HashMap;
import java.util.Map;

// 变量环境（作用域）
public class Environment {
    private final Map<String, RuntimeValue> variables = new HashMap<>();
    private final Environment parent;

    public Environment() { this(null); }
    public Environment(Environment parent) { this.parent = parent; }

    // 定义变量
    public void define(String name, RuntimeValue value) {
        variables.put(name, value);
    }

    // 查找变量（向上递归）
    public RuntimeValue lookup(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        if (parent != null) {
            return parent.lookup(name);
        }
        throw new RuntimeException("变量未定义: " + name);
    }
}