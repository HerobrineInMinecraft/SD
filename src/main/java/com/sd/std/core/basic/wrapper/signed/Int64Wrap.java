package com.sd.std.core.basic.wrapper.signed;

import com.sd.std.core.basic.wrapper.base.BaseNumberWrap;

public class Int64Wrap extends BaseNumberWrap {
    private final long value;

    public Int64Wrap(long val) {
        this.value = val;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toStr() {
        return String.valueOf(value);
    }

    @Override
    public int toInt() {
        return (int) value;
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

    public Int64Wrap add(Int64Wrap o) {
        return new Int64Wrap(value + o.value);
    }
    public Int64Wrap sub(Int64Wrap o) {
        return new Int64Wrap(value - o.value);
    }
}