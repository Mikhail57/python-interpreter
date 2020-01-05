package ru.mustakimov.pascal

import ru.mustakimov.pascal.exception.UnknownTokenException
import ru.mustakimov.pascal.token.ReservedKeywords
import ru.mustakimov.pascal.token.Token
import ru.mustakimov.pascal.token.TokenType

class Lexer(
    private val text: String
) {
    private var currentPos: Int = 0
    private var currentChar: Char? = text.getOrNull(currentPos)
    //    private val currentChar: Char?
//        get() = text.getOrNull(currentPos)
    private val nextChar: Char?
        get() = text.getOrNull(currentPos + 1)

    @Throws(UnknownTokenException::class)
    fun nextToken(): Token {
        while (currentChar != null) {
            val char = currentChar!!
            if (char.isWhitespace()) {
                skip()
                continue
            }
            return when {
                char.isDigit() -> Token(TokenType.INTEGER, integer())
                char.isLetter() -> id()
                char == '+' -> Token(TokenType.PLUS, "+").also { forward() }
                char == '-' -> Token(TokenType.MINUS, "-").also { forward() }
                char == '*' -> Token(TokenType.MUL, "*").also { forward() }
                char == '/' -> Token(TokenType.DIV, "/").also { forward() }
                char == '(' -> Token(TokenType.LPAREN, "(").also { forward() }
                char == ')' -> Token(TokenType.RPAREN, ")").also { forward() }
                char == ';' -> Token(TokenType.SEMI, ";").also { forward() }
                char == '.' -> Token(TokenType.DOT, ".").also { forward() }
                char == ':' && nextChar == '=' -> Token(TokenType.ASSIGN, ":=").also { forward(); forward() }
                else -> throw UnknownTokenException("Unknown token $char")
            }
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
        currentChar = text.getOrNull(currentPos)
    }

    private fun skip() {
        while (currentChar?.isWhitespace() == true) {
            forward()
        }
    }

    private fun id(): Token {
        val titleBuilder = StringBuilder()
        while (currentChar?.isLetter() == true) {
            titleBuilder.append(currentChar!!)
            forward()
        }
        val title = titleBuilder.toString()
        return try {
            ReservedKeywords.valueOf(title).token
        } catch (e: IllegalArgumentException) {
            Token(TokenType.ID, title)
        }
    }
}