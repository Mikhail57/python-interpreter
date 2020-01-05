package ru.mustakimov.pascal

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class InterpreterTest {

    @TestFactory
    fun testSimpleCases() = listOf(
        Pair("Should compute valid simple sum", "1 + 2") to 3f,
        Pair("Should compute valid simple diff", "5 - 2") to 3f,
        Pair("Should compute valid complex sum", "1 + 2 + 482 + 185236") to 185_721f,
        Pair("Should compute valid complex diff", "185236 - 15 - 18 - 96571") to 88_632f,
        Pair("Should compute valid unary minus", "-5") to -5f,
        Pair("Should compute valid unary plus", "+5") to 5f,
        Pair("Should compute valid double unary minus", "--5") to 5f,
        Pair("Should compute valid complex expression", "5 - - - + - (3 + 4) - +2") to 10f
    ).map {
        val (testTitle, text) = it.first
        val expectedResult = it.second
        DynamicTest.dynamicTest(testTitle) {
            assertEquals(expectedResult, Interpreter(Parser(Lexer(text))).interpret())
        }
    }

}