package com.sd.std.core.basic.wrapper.floatnum;

import com.sd.std.core.basic.wrapper.base.BaseNumberWrap;

public class Float32Wrap extends BaseNumberWrap {
    private final float value;

    public Float32Wrap(float val) {
        this.value = val;
    }

    public float getValue() {
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
        return (long) value;
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
        return value == 0.0f;
    }

    @Override
    public boolean isPositive() {
        return value > 0;
    }

    @Override
    public boolean isNegative() {
        return value < 0;
    }

    public boolean isNaN() {
        return Float.isNaN(value);
    }
    public boolean isInfinite() {
        return Float.isInfinite(value);
    }
}