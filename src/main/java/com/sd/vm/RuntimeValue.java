package com.sd.vm;

// 虚拟机运行时的值（统一int/float/bool/string）
public class RuntimeValue {
    public enum Type { INT, FLOAT, BOOL, STRING, NULL }

    public final Type type;
    public final Object value;

    private RuntimeValue(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    // 工厂方法
    public static RuntimeValue intVal(int v) { return new RuntimeValue(Type.INT, v); }
    public static RuntimeValue floatVal(double v) { return new RuntimeValue(Type.FLOAT, v); }
    public static RuntimeValue boolVal(boolean v) { return new RuntimeValue(Type.BOOL, v); }
    public static RuntimeValue stringVal(String v) { return new RuntimeValue(Type.STRING, v); }
    public static RuntimeValue nullVal() { return new RuntimeValue(Type.NULL, null); }

    @Override
    public String toString() {
        return switch (type) {
            case INT -> value.toString();
            case FLOAT -> value.toString();
            case BOOL -> value.toString();
            case STRING -> "\"" + value + "\"";
            case NULL -> "null";
        };
    }
}