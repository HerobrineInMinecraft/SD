package com.sd.lexer;

public class Lexer {
    private final String source;
    private int pos;
    private int readPos;
    private char ch;
    private int line = 1;
    private int col = 0;

    public Lexer(String source) {
        this.source = source;
        this.pos = 0;
        this.readPos = 0;
        readChar();
    }

    // 读取下一个字符
    private void readChar() {
        if (readPos >= source.length()) {
            ch = 0;
        } else {
            ch = source.charAt(readPos);
        }
        pos = readPos;
        readPos++;
        col++;
        if (ch == '\n') {
            line++;
            col = 0;
        }
    }

    // 预览下一字符
    private char peekChar() {
        if (readPos >= source.length()) return 0;
        return source.charAt(readPos);
    }

    // 跳过空白
    private void skipWhitespace() {
        while (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n') {
            readChar();
        }
    }

    // 读取标识符/关键字
    private String readIdentifier() {
        int start = pos;
        // ✅ 正确规则：开头字母/下划线，后面可以跟数字
        while (isLetter(ch) || ch == '_' || isDigit(ch)) {
            readChar();
        }
        return source.substring(start, pos);
    }

    // 读取数字（整数/浮点数）
    private String readNumber() {
        int start = pos;
        while (isDigit(ch)) {
            readChar();
        }
        // 浮点判断
        if (ch == '.' && isDigit(peekChar())) {
            readChar();
            while (isDigit(ch)) {
                readChar();
            }
        }
        return source.substring(start, pos);
    }

    // 读取字符串
    private String readString() {
        readChar();
        int start = pos;
        while (ch != '"' && ch != 0) {
            readChar();
        }
        String str = source.substring(start, pos);
        readChar();
        return str;
    }

    // 读取字符常量
    private String readCharLit() {
        readChar();
        int start = pos;
        while (ch != '\'' && ch != 0) {
            readChar();
        }
        String c = source.substring(start, pos);
        readChar();
        return c;
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    // 获取下一个令牌
    public Token nextToken() {
        skipWhitespace();
        Token token;
        int curLine = line;
        int curCol = col;

        switch (ch) {
            case '(': token = new Token(TokenType.L_PAREN, "(", curLine, curCol); readChar(); break;
            case ')': token = new Token(TokenType.R_PAREN, ")", curLine, curCol); readChar(); break;
            case '{': token = new Token(TokenType.L_BRACE, "{", curLine, curCol); readChar(); break;
            case '}': token = new Token(TokenType.R_BRACE, "}", curLine, curCol); readChar(); break;
            case '[': token = new Token(TokenType.L_BRACKET, "[", curLine, curCol); readChar(); break;
            case ']': token = new Token(TokenType.R_BRACKET, "]", curLine, curCol); readChar(); break;
            case ',': token = new Token(TokenType.COMMA, ",", curLine, curCol); readChar(); break;
            case '.': token = new Token(TokenType.DOT, ".", curLine, curCol); readChar(); break;
            case ';': token = new Token(TokenType.SEMICOLON, ";", curLine, curCol); readChar(); break;
            case ':': token = new Token(TokenType.COLON, ":", curLine, curCol); readChar(); break;

            case '+': token = new Token(TokenType.PLUS, "+", curLine, curCol); readChar(); break;
            case '-': token = new Token(TokenType.MINUS, "-", curLine, curCol); readChar(); break;
            case '*': token = new Token(TokenType.STAR, "*", curLine, curCol); readChar(); break;
            case '/': token = new Token(TokenType.SLASH, "/", curLine, curCol); readChar(); break;

            case '=':
                if (peekChar() == '=') {
                    readChar();
                    token = new Token(TokenType.EQ_EQ, "==", curLine, curCol);
                } else {
                    token = new Token(TokenType.EQ, "=", curLine, curCol);
                }
                readChar();
                break;
            case '!':
                if (peekChar() == '=') {
                    readChar();
                    token = new Token(TokenType.BANG_EQ, "!=", curLine, curCol);
                } else {
                    token = new Token(TokenType.BANG, "!", curLine, curCol);
                }
                readChar();
                break;
            case '>':
                if (peekChar() == '=') {
                    readChar();
                    token = new Token(TokenType.GT_EQ, ">=", curLine, curCol);
                } else {
                    token = new Token(TokenType.GT, ">", curLine, curCol);
                }
                readChar();
                break;
            case '<':
                if (peekChar() == '=') {
                    readChar();
                    token = new Token(TokenType.LT_EQ, "<=", curLine, curCol);
                } else {
                    token = new Token(TokenType.LT, "<", curLine, curCol);
                }
                readChar();
                break;

            case '"':
                String strVal = readString();
                token = new Token(TokenType.STRING_LIT, strVal, curLine, curCol);
                break;
            case '\'':
                String charVal = readCharLit();
                token = new Token(TokenType.CHAR_LIT, charVal, curLine, curCol);
                break;

            case 0:
                token = new Token(TokenType.EOF, "", curLine, curCol);
                break;

            default:
                if (isLetter(ch) || ch == '_') {
                    String word = readIdentifier();
                    TokenType kwType = KeywordTable.getKeywordType(word);
                    if (kwType != null) {
                        token = new Token(kwType, word, curLine, curCol);
                    } else {
                        // i8/u32/f32 等全部归入 IDENTIFIER
                        token = new Token(TokenType.IDENTIFIER, word, curLine, curCol);
                    }
                } else if (isDigit(ch)) {
                    String num = readNumber();
                    if (num.contains(".")) {
                        token = new Token(TokenType.FLOAT_LIT, num, curLine, curCol);
                    } else {
                        token = new Token(TokenType.INT_LIT, num, curLine, curCol);
                    }
                } else {
                    // 未知字符
                    token = new Token(TokenType.IDENTIFIER, String.valueOf(ch), curLine, curCol);
                    readChar();
                }
                break;
        }
        return token;
    }
}
