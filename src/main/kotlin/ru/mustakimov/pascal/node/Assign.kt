package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.Token

data class Assign(
    val left: Node,
    val right: Node,
    val token: Token
) : Node