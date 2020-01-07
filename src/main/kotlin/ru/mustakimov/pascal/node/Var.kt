package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.Token

/**
 * AST node represent Variable
 */
class Var(
    token: Token
) : Node {
    /**
     * Variable name
     */
    val value: String = token.value!!

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Var

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}