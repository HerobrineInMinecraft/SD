package com.sd.std.core.basic.wrapper.simple;

public class CharWrap {
    private final char value;

    public CharWrap(char val) {
        this.value = val;
    }

    public char getValue() {
        return value;
    }

    public int getAscii() {
        return value;
    }

    public boolean isLetter() {
        return Character.isLetter(value);
    }

    public boolean isDigit() {
        return Character.isDigit(value);
    }
}