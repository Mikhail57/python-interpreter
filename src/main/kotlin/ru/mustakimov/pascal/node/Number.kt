package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.Token

/**
 * AST representation of number. Currently interpreted as REAL
 */
data class Number(
    /**
     * Token, that contains value of the number
     */
    val token: Token
) : Node