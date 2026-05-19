package com.sd.std.core.basic.types;

import com.sd.module.ModuleContextRegistry;
import java.util.Set;

public final class BasicTypes {

    // 所有基础类型名称
    public static final Set<String> TYPE_NAMES = Set.of(
            "void",
            "bool",
            "i8", "i16", "i32", "i64",
            "u8", "u16", "u32", "u64",
            "f32", "f64",
            "char", "str",
            "rawptr", "nullable", "unit"
    );

    // 注册到模块系统
    public static void register() {
        // 把 TYPE_NAMES 改成正确的变量名
        ModuleContextRegistry.registerModule("sd.core.basic.types", TYPE_NAMES);
    }
}