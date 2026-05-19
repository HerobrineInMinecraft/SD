package com.sd.std.core.basic.struct;

import com.sd.module.ModuleContextRegistry;
import java.util.Set;

public final class StructStd {
    public static final Set<String> STRUCT_TYPES = Set.of(
            "SdStruct",
            "StructType",
            "StructMember"
    );

    public static void register() {
        ModuleContextRegistry.registerModule(
                "sd.core.basic.struct",
                STRUCT_TYPES
        );
    }
}