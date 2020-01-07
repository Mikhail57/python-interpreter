package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.Token

/**
 * AST node representation of Binary Operation.
 */
data class BinOp(
    /**
     * Left side of binary operation
     */
    val left: Node,

    /**
     * Right side of binary operation
     */
    val right: Node,

    /**
     * Operation to be preformed
     */
    val op: Token
) : Node