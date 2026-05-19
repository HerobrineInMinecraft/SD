package com.sd.std.core.basic.struct;

public class StructMember {
    private final String name;
    private final String type;

    public StructMember(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public String getType() { return type; }
}