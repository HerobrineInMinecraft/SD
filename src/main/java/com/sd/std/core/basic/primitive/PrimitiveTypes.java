package com.sd.std.core.basic.primitive;

import com.sd.module.ModuleContextRegistry;
import java.util.Set;

public final class PrimitiveTypes {

    // 原生基础类型（真正的底层类型）
    public static final Set<String> PRIMITIVE_NAMES = Set.of(
            "u8", "u16", "u32", "u64",
            "i8", "i16", "i32", "i64",
            "f32", "f64",
            "bool", "byte", "unit"
    );

    public static void register() {
        ModuleContextRegistry.registerModule(
                "sd.core.basic.primitive",  // 模块名
                PRIMITIVE_NAMES
        );
    }
}