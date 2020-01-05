package ru.mustakimov.pascal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.mustakimov.pascal.token.Token
import ru.mustakimov.pascal.token.TokenType


internal class TokenTest {
    @Test
    fun `Token should be creatable with null as value`() {
        Assertions.assertDoesNotThrow {
            Token(TokenType.EOF, null)
        }
    }
}