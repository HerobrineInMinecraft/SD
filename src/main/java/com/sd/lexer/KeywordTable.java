package com.sd.lexer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class KeywordTable {
    public static final Map<String, TokenType> KEYWORDS;

    static {
        Map<String, TokenType> map = new HashMap<>();

        // 绑定关键字
        map.put("fn", TokenType.FN);
        map.put("let", TokenType.LET);
        map.put("mut", TokenType.MUT);
        map.put("const", TokenType.CONST);
        map.put("static", TokenType.STATIC);
        map.put("pub", TokenType.PUB);
        map.put("priv", TokenType.PRIV);
        map.put("extern", TokenType.EXTERN);
        map.put("type", TokenType.TYPE);
        map.put("impl", TokenType.IMPL);
        map.put("as", TokenType.AS);

        map.put("struct", TokenType.STRUCT);
        map.put("enum", TokenType.ENUM);
        map.put("union", TokenType.UNION);
        map.put("trait", TokenType.TRAIT);
        map.put("where", TokenType.WHERE);

        map.put("if", TokenType.IF);
        map.put("else", TokenType.ELSE);
        map.put("match", TokenType.MATCH);
        map.put("for", TokenType.FOR);
        map.put("while", TokenType.WHILE);
        map.put("loop", TokenType.LOOP);
        map.put("break", TokenType.BREAK);
        map.put("continue", TokenType.CONTINUE);
        map.put("return", TokenType.RETURN);
        map.put("yield", TokenType.YIELD);

        map.put("true", TokenType.TRUE);
        map.put("false", TokenType.FALSE);
        map.put("void", TokenType.VOID);

        map.put("import", TokenType.IMPORT);
        map.put("use", TokenType.USE);
        map.put("package", TokenType.PACKAGE);

        map.put("unsafe", TokenType.UNSAFE);
        map.put("catch", TokenType.CATCH);
        map.put("throw", TokenType.THROW);

        KEYWORDS = Collections.unmodifiableMap(map);
    }

    // 查询是否为硬关键字
    public static TokenType getKeywordType(String word) {
        return KEYWORDS.getOrDefault(word, null);
    }
}