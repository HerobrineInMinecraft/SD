package com.sd.std.core.basic.wrapper.unsigned;

import com.sd.std.core.basic.wrapper.base.BaseNumberWrap;

public class UInt8Wrap extends BaseNumberWrap {
    private final short value;

    public UInt8Wrap(short val) {
        this.value = (short) (val & 0xFF);
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
        return false;
    }
}