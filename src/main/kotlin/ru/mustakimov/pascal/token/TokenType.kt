package ru.mustakimov.pascal.token

enum class TokenType {
    INTEGER,
    REAL,

    PLUS,
    MINUS,
    MUL,
    DIV,

    LPAREN,
    RPAREN,

    ASSIGN,
    ID,

    PROGRAM,
    BEGIN,
    END,

    SEMI,
    DOT,

    /**
     * No more tokens left for parsing
     */
    EOF
}