package ru.mustakimov.pascal

import ru.mustakimov.pascal.exception.UnknownTokenException
import ru.mustakimov.pascal.token.Token
import ru.mustakimov.pascal.token.TokenType

class Lexer(
    private val text: String
) {
    private var currentPos: Int = 0
    private val currentChar: Char?
        get() = text.getOrNull(currentPos)

    @Throws(UnknownTokenException::class)
    fun nextToken(): Token {
        while (currentChar != null) {
            val char = currentChar!!
            if (char.isWhitespace()) {
                skip()
                continue
            }
            if (char.isDigit()) {
                return Token(TokenType.INTEGER, integer())
            }
            return when (char) {
                '+' -> Token(TokenType.PLUS, "+")
                '-' -> Token(TokenType.MINUS, "-")
                '*' -> Token(TokenType.MUL, "*")
                '/' -> Token(TokenType.DIV, "/")
                '(' -> Token(TokenType.LPAREN, "(")
                ')' -> Token(TokenType.RPAREN, ")")
                else -> throw UnknownTokenException("Unknown token $char")
            }.also { forward() }
        }
        return Token(TokenType.EOF, null)
    }

    private fun integer(): String {
        val result = StringBuilder()
        while (currentChar?.isDigit() == true) {
            result.append(currentChar!!)
            forward()
        }
        return result.toString()
    }

    private fun forward() {
        currentPos++
    }

    private fun skip() {
        while (currentChar?.isWhitespace() == true) {
            forward()
        }
    }
}