package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.Token

/**
 * AST node represent Variable
 */
data class Var(
    val token: Token
) : Node {
    /**
     * Variable name
     */
    val value: String = token.value!!
}