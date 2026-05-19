package com.sd.std.core.basic.wrapper.signed;

import com.sd.std.core.basic.wrapper.base.BaseNumberWrap;

public class Int8Wrap extends BaseNumberWrap {
    private final byte value;

    public Int8Wrap(byte val) {
        this.value = val;
    }

    public byte getValue() {
        return value;
    }

    @Override
    public String toStr() {
        return String.valueOf(value);
    }

    @Override
    public int toInt() {
        return value;
    }

    @Override
    public long toLong() {
        return value;
    }

    @Override
    public float toFloat() {
        return value;
    }

    @Override
    public double toDouble() {
        return value;
    }

    @Override
    public boolean isZero() {
        return value == 0;
    }

    @Override
    public boolean isPositive() {
        return value > 0;
    }

    @Override
    public boolean isNegative() {
        return value < 0;
    }

    public Int8Wrap add(Int8Wrap other) {
        return new Int8Wrap((byte)(this.value + other.value));
    }
    public Int8Wrap sub(Int8Wrap other) {
        return new Int8Wrap((byte)(this.value - other.value));
    }
}