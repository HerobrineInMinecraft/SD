package com.sd.std.core.basic.struct.base;

public interface SdStruct {
    String structName();
    Object getField(String name);
    void setField(String name, Object value);
}