package com.sd.std.core.basic.types;

import com.sd.module.ModuleContextRegistry;

public final class StdLibTypes {
    // 注册：把 types 库加入全局模块系统
    public static void register() {
        ModuleContextRegistry.registerModule(
                "sd.core.basic.types",
                BasicTypes.TYPE_NAMES
        );
    }
}