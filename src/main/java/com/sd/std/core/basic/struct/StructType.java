package com.sd.std.core.basic.struct;

import java.util.List;

public class StructType {
    private final String name;
    private final List<StructMember> members;

    public StructType(String name, List<StructMember> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() { return name; }
    public List<StructMember> getMembers() { return members; }

    public StructMember getMember(String name) {
        for (StructMember m : members) {
            if (m.getName().equals(name)) return m;
        }
        return null;
    }
}