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

    VAR,
    BEGIN,
    END,
    PROGRAM,

    SEMI,
    DOT,

    /**
     * No more tokens left for parsing
     */
    EOF
}