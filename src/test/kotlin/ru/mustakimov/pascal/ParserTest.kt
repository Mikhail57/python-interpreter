package ru.mustakimov.pascal

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.mustakimov.pascal.node.BinOp
import ru.mustakimov.pascal.node.Number
import ru.mustakimov.pascal.node.UnaryOp
import ru.mustakimov.pascal.token.CommonTokens
import ru.mustakimov.pascal.token.Token
import ru.mustakimov.pascal.token.TokenType

internal class ParserTest {

    @Test
    fun `Should parse simple sum`() {
        val text = "1 + 2"
        val parser = Parser(Lexer(text))
        val node = parser.parse()
        assertEquals(
            BinOp(
                left = Number(Token(TokenType.INTEGER, "1")),
                op = Token(TokenType.PLUS, "+"),
                right = Number(Token(TokenType.INTEGER, "2"))
            ),
            node
        )
    }

    @Test
    fun `Should parse simple diff`() {
        val text = "10 - 2"
        val parser = Parser(Lexer(text))
        val node = parser.parse()
        assertEquals(
            BinOp(
                left = Number(Token(TokenType.INTEGER, "10")),
                op = Token(TokenType.MINUS, "-"),
                right = Number(Token(TokenType.INTEGER, "2"))
            ),
            node
        )
    }

    @Test
    fun `Should parse simple mult`() {
        val text = "10 * 2"
        val parser = Parser(Lexer(text))
        val node = parser.parse()
        assertEquals(
            BinOp(
                left = Number(Token(TokenType.INTEGER, "10")),
                op = Token(TokenType.MUL, "*"),
                right = Number(Token(TokenType.INTEGER, "2"))
            ),
            node
        )
    }

    @Test
    fun `Should parse complex sum expression with parentheses`() {
        val text = "5 - - - + - (3 + 4) - +2"
        val parser = Parser(Lexer(text))
        val node = parser.parse()
        assertEquals(
            BinOp(
                left = BinOp(
                    left = Number(Token(TokenType.INTEGER, "5")),
                    op = Token(TokenType.MINUS, "-"),
                    right = UnaryOp(
                        CommonTokens.MINUS,
                        UnaryOp(
                            CommonTokens.MINUS,
                            UnaryOp(
                                CommonTokens.PLUS,
                                UnaryOp(
                                    CommonTokens.MINUS,
                                    BinOp(
                                        left = Number(
                                            Token(
                                                TokenType.INTEGER,
                                                "3"
                                            )
                                        ),
                                        op = CommonTokens.PLUS,
                                        right = Number(
                                            Token(
                                                TokenType.INTEGER,
                                                "4"
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                ),
                op = Token(TokenType.MINUS, "-"),
                right = UnaryOp(
                    Token(TokenType.PLUS, "+"), Number(
                        Token(TokenType.INTEGER, "2")
                    )
                )
            ),
            node
        )
    }

}