package com.sd.std.core.basic.wrapper.simple;

public class ByteWrap {
    private final byte value;

    public ByteWrap(byte val) {
        this.value = val;
    }

    public byte getValue() {
        return value;
    }

    public String toHex() {
        return String.format("%02X", value);
    }
}