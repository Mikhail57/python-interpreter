package ru.mustakimov.pascal.node

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NoOpTest {

    @Test
    fun testEquals() {
        assertEquals(true, NoOp() == NoOp())
    }

    @Test
    fun testHashCode() {
        assertEquals(true, NoOp().hashCode() == NoOp().hashCode())
    }
}