package com.sd.std.core.basic.wrapper;

import com.sd.module.ModuleContextRegistry;
import java.util.Set;

public final class WrapperStd {
    // 该库对外暴露可识别包装类型名
    public static final Set<String> WRAPPER_TYPES = Set.of(
            "Int8Wrap","Int16Wrap","Int32Wrap","Int64Wrap",
            "UInt8Wrap","UInt16Wrap","UInt32Wrap","UInt64Wrap",
            "Float32Wrap","Float64Wrap","BoolWrap","ByteWrap","CharWrap"
    );

    public static void register(){
        ModuleContextRegistry.registerModule(
                "sd.core.basic.wrapper",
                WRAPPER_TYPES
        );
    }
}