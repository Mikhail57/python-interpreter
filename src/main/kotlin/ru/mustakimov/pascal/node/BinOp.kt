package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.Token

data class BinOp(
    val left: Node,
    val right: Node,
    val op: Token
) : Node