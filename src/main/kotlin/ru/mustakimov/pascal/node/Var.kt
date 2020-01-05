package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.Token

data class Var(
    val token: Token
) : Node {
    val value: String?
        get() = token.value
}