package com.sd.lexer;

public enum TokenType {
    // 括号分隔符
    L_PAREN,    // (
    R_PAREN,    // )
    L_BRACE,    // {
    R_BRACE,    // }
    L_BRACKET,  // [
    R_BRACKET,  // ]

    // 标点符号
    COMMA,      // ,
    DOT,        // .
    SEMICOLON,  // ;
    COLON,      // :

    // 算术运算符
    PLUS,       // +
    MINUS,      // -
    STAR,       // *
    SLASH,      // /

    // 比较运算符
    EQ,         // =
    EQ_EQ,      // ==
    BANG,       // !
    BANG_EQ,    // !=
    GT,         // >
    LT,         // <
    GT_EQ,      // >=
    LT_EQ,      // <=

    // 全局硬关键字
    FN, LET, MUT, CONST, STATIC,
    PUB, PRIV, EXTERN, TYPE, IMPL, AS,
    STRUCT, ENUM, UNION, TRAIT, WHERE,
    IF, ELSE, MATCH, FOR, WHILE, LOOP,
    BREAK, CONTINUE, RETURN, YIELD,
    TRUE, FALSE, VOID,
    IMPORT, USE, PACKAGE,
    UNSAFE, CATCH, THROW,

    // 字面量
    INT_LIT,
    FLOAT_LIT,
    CHAR_LIT,
    STRING_LIT,

    // 通用标识
    IDENTIFIER,
    EOF
}