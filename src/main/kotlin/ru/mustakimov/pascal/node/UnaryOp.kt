package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.Token

data class UnaryOp(
    val token: Token,
    val expr: Node
) : Node