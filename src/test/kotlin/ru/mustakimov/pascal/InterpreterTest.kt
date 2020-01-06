package ru.mustakimov.pascal

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import ru.mustakimov.pascal.exception.UnexpectedTokenException

internal class InterpreterTest {

    @Test
    fun `Should throw exception`() {
        val text = "5 * 2"
        assertThrows(UnexpectedTokenException::class.java) {
            Interpreter(Parser(Lexer(text))).interpret()
        }
    }

    @Test
    fun `Should produce nothing`() {
        val text = "BEGIN END."
        assertDoesNotThrow {
            Interpreter.newInstance(text).interpret()
        }
    }

    @Test
    fun `Should handle variables`() {
        val text = """
            BEGIN
                BEGIN
                    number := 2;
                    a := number;
                    b := 10 * a + 10 * number / 4;
                    c := a - - b;
                    d := a + +b
                END;
                x := 11;
            END.
        """.trimIndent()
        val interpret = Interpreter.newInstance(text)
        assertDoesNotThrow {
            interpret.interpret()
        }
        assertEquals(2f, interpret.variables["number"])
        assertEquals(11f, interpret.variables["x"])
        assertEquals(2f, interpret.variables["a"])
        assertEquals(25f, interpret.variables["b"])
        assertEquals(27f, interpret.variables["c"])
    }

}