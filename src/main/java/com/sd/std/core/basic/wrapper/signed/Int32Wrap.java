package com.sd.std.core.basic.wrapper.signed;

import com.sd.std.core.basic.wrapper.base.BaseNumberWrap;

public class Int32Wrap extends BaseNumberWrap {
    private final int value;

    public Int32Wrap(int val) {
        this.value = val;
    }

    public int getValue() {
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

    public Int32Wrap add(Int32Wrap o) {
        return new Int32Wrap(value + o.value);
    }
    public Int32Wrap sub(Int32Wrap o) {
        return new Int32Wrap(value - o.value);
    }
    public Int32Wrap mul(Int32Wrap o) {
        return new Int32Wrap(value * o.value);
    }
    public Int32Wrap div(Int32Wrap o) {
        return new Int32Wrap(value / o.value);
    }
}