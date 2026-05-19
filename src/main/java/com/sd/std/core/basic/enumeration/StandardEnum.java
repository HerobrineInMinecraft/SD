package com.sd.std.core.basic.enumeration;

import com.sd.std.core.basic.enumeration.base.SdEnum;

public class StandardEnum implements SdEnum {
    private final String name;
    private final int ordinal;

    public StandardEnum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    @Override
    public int ordinal() {
        return ordinal;
    }

    @Override
    public String name() {
        return name;
    }

    public boolean isEqual(StandardEnum other) {
        return this.ordinal == other.ordinal;
    }
}