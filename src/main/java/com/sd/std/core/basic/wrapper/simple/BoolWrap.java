package com.sd.std.core.basic.wrapper.simple;

public class BoolWrap {
    private final boolean value;

    public BoolWrap(boolean val) {
        this.value = val;
    }

    public boolean getValue() {
        return value;
    }

    public String toStr() {
        return value ? "true" : "false";
    }

    public BoolWrap not() {
        return new BoolWrap(!value);
    }
}