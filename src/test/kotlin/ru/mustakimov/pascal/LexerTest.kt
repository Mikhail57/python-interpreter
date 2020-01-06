package ru.mustakimov.pascal

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.mustakimov.pascal.exception.UnknownTokenException
import ru.mustakimov.pascal.token.CommonTokens
import ru.mustakimov.pascal.token.Token
import ru.mustakimov.pascal.token.TokenType

internal class LexerTest {

    @Test
    fun `Should parse one-digit integers`() {
        val text = "5"
        val lexer = Lexer(text)
        assertEquals(Token(TokenType.INTEGER, "5"), lexer.nextToken())
    }

    @Test
    fun `Should parse integers`() {
        val text = "1123454684345453432131"
        val lexer = Lexer(text)
        assertEquals(Token(TokenType.INTEGER, "1123454684345453432131"), lexer.nextToken())
    }

    @Test
    fun `Should parse plus`() {
        val text = "+"
        val lexer = Lexer(text)
        assertEquals(Token(TokenType.PLUS, "+"), lexer.nextToken())
    }

    @Test
    fun `Should parse minus`() {
        val text = "-"
        val lexer = Lexer(text)
        assertEquals(Token(TokenType.MINUS, "-"), lexer.nextToken())
    }

    @Test
    fun `Should parse multiply`() {
        val text = "*"
        val lexer = Lexer(text)
        assertEquals(Token(TokenType.MUL, "*"), lexer.nextToken())
    }

    @Test
    fun `Should parse divide`() {
        val text = "/"
        val lexer = Lexer(text)
        assertEquals(Token(TokenType.DIV, "/"), lexer.nextToken())
    }

    @Test
    fun `Should parse parenthesis`() {
        val text = "()"
        val lexer = Lexer(text)
        assertEquals(Token(TokenType.LPAREN, "("), lexer.nextToken())
        assertEquals(Token(TokenType.RPAREN, ")"), lexer.nextToken())
    }

    @Test
    fun `Should parse complex statement`() {
        val text = "(1++5*6)/7"
        val lexer = Lexer(text)
        assertEquals(Token(TokenType.LPAREN, "("), lexer.nextToken())
        assertEquals(Token(TokenType.INTEGER, "1"), lexer.nextToken())
        assertEquals(Token(TokenType.PLUS, "+"), lexer.nextToken())
        assertEquals(Token(TokenType.PLUS, "+"), lexer.nextToken())
        assertEquals(Token(TokenType.INTEGER, "5"), lexer.nextToken())
        assertEquals(Token(TokenType.MUL, "*"), lexer.nextToken())
        assertEquals(Token(TokenType.INTEGER, "6"), lexer.nextToken())
        assertEquals(Token(TokenType.RPAREN, ")"), lexer.nextToken())
        assertEquals(Token(TokenType.DIV, "/"), lexer.nextToken())
        assertEquals(Token(TokenType.INTEGER, "7"), lexer.nextToken())
    }

    @Test
    fun `Should handle spaces`() {
        val text = "             5 +       3         "
        val lexer = Lexer(text)
        assertEquals(Token(TokenType.INTEGER, "5"), lexer.nextToken())
        assertEquals(Token(TokenType.PLUS, "+"), lexer.nextToken())
        assertEquals(Token(TokenType.INTEGER, "3"), lexer.nextToken())
        assertEquals(Token(TokenType.EOF, null), lexer.nextToken())
    }

    @Test
    fun `Should throw an exception on unknown char`() {
        val text = "₽"
        val lexer = Lexer(text)
        assertThrows(UnknownTokenException::class.java) {
            lexer.nextToken()
        }
    }

    @Test
    fun `Should throw an exception on wrong program`() {
        val text = "BEGIN : END."
        val lexer = Lexer(text)
        assertThrows(UnknownTokenException::class.java) {
            lexer.nextToken()
            lexer.nextToken()
        }
    }

    @Test
    fun `Should handle basic pascal program`() {
        val text = "BEGIN END."
        val lexer = Lexer(text)
        assertEquals(CommonTokens.BEGIN, lexer.nextToken())
        assertEquals(CommonTokens.END, lexer.nextToken())
        assertEquals(CommonTokens.DOT, lexer.nextToken())
        assertEquals(CommonTokens.EOF, lexer.nextToken())
    }

    @Test
    fun `Should handle basic pascal program with one assignment`() {
        val text = "BEGIN a := 2; END."
        val lexer = Lexer(text)
        assertEquals(CommonTokens.BEGIN, lexer.nextToken())
        assertEquals(Token(TokenType.ID, "a"), lexer.nextToken())
        assertEquals(CommonTokens.ASSIGN, lexer.nextToken())
        assertEquals(Token(TokenType.INTEGER, "2"), lexer.nextToken())
        assertEquals(CommonTokens.SEMI, lexer.nextToken())
        assertEquals(CommonTokens.END, lexer.nextToken())
        assertEquals(CommonTokens.DOT, lexer.nextToken())
        assertEquals(CommonTokens.EOF, lexer.nextToken())
    }

    @Test
    fun `Should handle basic pascal program with multiple assignment`() {
        val text = "BEGIN a := 2; END."
        val lexer = Lexer(text)
        assertEquals(CommonTokens.BEGIN, lexer.nextToken())
        assertEquals(Token(TokenType.ID, "a"), lexer.nextToken())
        assertEquals(CommonTokens.ASSIGN, lexer.nextToken())
        assertEquals(Token(TokenType.INTEGER, "2"), lexer.nextToken())
        assertEquals(CommonTokens.SEMI, lexer.nextToken())
        assertEquals(CommonTokens.END, lexer.nextToken())
        assertEquals(CommonTokens.DOT, lexer.nextToken())
        assertEquals(CommonTokens.EOF, lexer.nextToken())
    }

}