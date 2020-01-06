package ru.mustakimov.pascal.node

import ru.mustakimov.pascal.token.CommonTokens
import ru.mustakimov.pascal.token.Token
import ru.mustakimov.pascal.token.TokenType

fun makeNumber(value: String) = Number(Token(TokenType.INTEGER, value))
fun <T : kotlin.Number> makeNumber(value: T) = Number(Token(TokenType.INTEGER, value.toString()))

fun makeVariable(name: String) = Var(Token(TokenType.ID, name))

fun makeMinus(left: Node, right: Node) = BinOp(left = left, right = right, op = CommonTokens.MINUS)
fun makePlus(left: Node, right: Node) = BinOp(left = left, right = right, op = CommonTokens.PLUS)
fun makeDiv(left: Node, right: Node) = BinOp(left = left, right = right, op = CommonTokens.DIV)
fun makeMul(left: Node, right: Node) = BinOp(left = left, right = right, op = CommonTokens.MUL)

fun makeUnaryMinus(expr: Node) = UnaryOp(CommonTokens.MINUS, expr)
