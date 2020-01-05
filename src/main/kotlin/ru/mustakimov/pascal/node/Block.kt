package ru.mustakimov.pascal.node

/**
 * AST node represent `BEGIN ... END` block
 */
data class Block(
    val children: List<Node>
) : Node