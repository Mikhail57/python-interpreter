package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.Token

/**
 * AST representation of unary operation (usually PLUS or MINUS)
 */
data class UnaryOp(
    /**
     * Operation to be preformed on [expr]
     */
    val token: Token,
    /**
     * Expression result of that will be modified by [token]
     */
    val expr: Node
) : Node