package com.sd.module;

import java.util.HashSet;
import java.util.Set;

public class FileImportContext {
    private final Set<String> importedModules = new HashSet<>();

    public void importModule(String moduleName) {
        importedModules.add(moduleName);
    }

    // 遍历所有导入的模块，检查类型是否存在
    public boolean isContextIdentifier(String name) {
        for (String mod : importedModules) {
            if (ModuleContextRegistry.isContextIdentifier(mod, name)) {
                return true;
            }
        }
        return false;
    }
}