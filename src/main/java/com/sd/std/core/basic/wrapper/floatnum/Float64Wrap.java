package com.sd.std.core.basic.wrapper.floatnum;

import com.sd.std.core.basic.wrapper.base.BaseNumberWrap;

public class Float64Wrap extends BaseNumberWrap {
    private final double value;

    public Float64Wrap(double val) {
        this.value = val;
    }

    public double getValue() {
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
        return (float) value;
    }

    @Override
    public double toDouble() {
        return value;
    }

    @Override
    public boolean isZero() {
        return value == 0.0d;
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
        return Double.isNaN(value);
    }
    public boolean isInfinite() {
        return Double.isInfinite(value);
    }
}