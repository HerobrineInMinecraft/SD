package com.sd.std.core.basic.wrapper.signed;

import com.sd.std.core.basic.wrapper.base.BaseNumberWrap;

public class Int16Wrap extends BaseNumberWrap {
    private final short value;

    public Int16Wrap(short val) {
        this.value = val;
    }

    public short getValue() {
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

    public Int16Wrap add(Int16Wrap o) {
        return new Int16Wrap((short)(value + o.value));
    }
    public Int16Wrap sub(Int16Wrap o) {
        return new Int16Wrap((short)(value - o.value));
    }
}