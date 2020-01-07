package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.CommonTokens
import ru.mustakimov.pascal.token.Token

/**
 * AST node representation of assign
 */
data class Assign(
    /**
     * Left part of assign expression. Currently must be [Var]
     */
    val left: Node,
    /**
     * Right part of assign expression
     */
    val right: Node
) : Node {
    val token: Token = CommonTokens.ASSIGN
}