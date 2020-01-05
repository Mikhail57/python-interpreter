package ru.mustakimov.pascal.token

enum class ReservedKeywords(val token: Token) {
    BEGIN(Token(TokenType.BEGIN, "BEGIN")),
    END(Token(TokenType.END, "END"))
}