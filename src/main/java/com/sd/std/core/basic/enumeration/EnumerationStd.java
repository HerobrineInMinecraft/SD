package com.sd.std.core.basic.enumeration;

import com.sd.module.ModuleContextRegistry;
import java.util.Set;

public final class EnumerationStd {
    public static final Set<String> ENUM_TYPE_NAMES = Set.of(
            "SdEnum",
            "StandardEnum"
    );

    public static void register() {
        ModuleContextRegistry.registerModule(
                "sd.core.basic.enumeration",
                ENUM_TYPE_NAMES
        );
    }
}