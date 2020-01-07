package ru.mustakimov.pascal.token

enum class TokenType {
    /**
     * Some number
     */
    INTEGER,

    /**
     * Plus (`+`)
     */
    PLUS,
    /**
     * Minus (`-`)
     */
    MINUS,
    /**
     * Multiplication (`*`)
     */
    MUL,
    /**
     * Division (`/`)
     */
    DIV,

    /**
     * Left parenthesis (`(`)
     */
    LPAREN,
    /**
     * Right parenthesis (`)`)
     */
    RPAREN,

    /**
     * Assign (`:=`)
     */
    ASSIGN,
    /**
     * Some ID (usually variable name)
     */
    ID,

    /**
     * `BEGIN` keyword
     */
    BEGIN,
    /**
     * `END` keyword
     */
    END,

    /**
     * Semicolon (`;`)
     */
    SEMI,
    /**
     * Dot (`.`)
     */
    DOT,

    /**
     * No more tokens left for parsing
     */
    EOF
}