package com.sd.module;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ModuleContextRegistry {
    private static final Map<String, Set<String>> MODULE_CONTEXTS = new HashMap<>();

    public static void registerModule(String moduleName, Set<String> identifiers) {
        MODULE_CONTEXTS.put(moduleName, identifiers);
    }

    public static Set<String> getContextIdentifiers(String moduleName) {
        return MODULE_CONTEXTS.getOrDefault(moduleName, Set.of());
    }

    // ↓↓↓ 这个方法必须改成遍历所有已导入模块 ↓↓↓
    public static boolean isContextIdentifier(String moduleName, String name) {
        Set<String> types = MODULE_CONTEXTS.get(moduleName);
        return types != null && types.contains(name);
    }
}