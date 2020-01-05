package ru.mustakimov.pascal.token

object CommonTokens {
    val EOF = Token(TokenType.EOF, null)
    val MINUS = Token(TokenType.MINUS, "-")
    val PLUS = Token(TokenType.PLUS, "+")
    val BEGIN = Token(TokenType.BEGIN, "BEGIN")
    val END = Token(TokenType.END, "END")
    val DOT = Token(TokenType.DOT, ".")
    val SEMI = Token(TokenType.SEMI, ";")
    val ASSIGN = Token(TokenType.ASSIGN, ":=")
}