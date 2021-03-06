package ru.mustakimov.pascal

import ru.mustakimov.pascal.exception.UnknownTokenException
import ru.mustakimov.pascal.token.CommonTokens
import ru.mustakimov.pascal.token.ReservedKeywords
import ru.mustakimov.pascal.token.Token
import ru.mustakimov.pascal.token.TokenType

class Lexer(
    private val text: String
) {
    private var currentPos: Int = 0
    private var currentChar: Char? = text.getOrNull(currentPos)
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
                char == '+' -> CommonTokens.PLUS.also { forward() }
                char == '-' -> CommonTokens.MINUS.also { forward() }
                char == '*' -> CommonTokens.MUL.also { forward() }
                char == '/' -> CommonTokens.DIV.also { forward() }
                char == '(' -> CommonTokens.LPAREN.also { forward() }
                char == ')' -> CommonTokens.RPAREN.also { forward() }
                char == ';' -> CommonTokens.SEMI.also { forward() }
                char == '.' -> CommonTokens.DOT.also { forward() }
                char == ':' && nextChar == '=' -> CommonTokens.ASSIGN.also { forward(); forward() }
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