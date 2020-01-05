package ru.mustakimov.pascal

import ru.mustakimov.pascal.node.BinOp
import ru.mustakimov.pascal.node.Node
import ru.mustakimov.pascal.node.Number
import ru.mustakimov.pascal.node.UnaryOp
import ru.mustakimov.pascal.token.TokenType

class Interpreter internal constructor(
    private val parser: Parser
) : NodeVisitor {

    fun interpret(): Float {
        val tree = parser.parse()
        return visit(tree) as Float
    }

    override fun visit(node: Node): Any {
        return when (node) {
            is BinOp -> visitBinOp(node)
            is Number -> visitNumber(node)
            is UnaryOp -> visitUnaryOp(node)
            else -> throw UnsupportedOperationException()
        }
    }

    private fun visitUnaryOp(node: UnaryOp): Float {
        return when (node.token.type) {
            TokenType.MINUS -> -(visit(node.expr) as Float)
            TokenType.PLUS -> visit(node.expr) as Float
            else -> throw UnknownError()
        }
    }

    private fun visitNumber(node: Number): Float {
        return node.token.value?.toFloat() ?: throw UnknownError()
    }

    private fun visitBinOp(node: BinOp): Float {
        return when (node.op.type) {
            TokenType.PLUS -> visit(node.left) as Float + visit(node.right) as Float
            TokenType.MINUS -> visit(node.left) as Float - visit(node.right) as Float
            TokenType.DIV -> visit(node.left) as Float / visit(node.right) as Float
            TokenType.MUL -> visit(node.left) as Float * visit(node.right) as Float
            else -> throw UnsupportedOperationException()
        }
    }

    companion object {
        fun newInstance(text: String): Interpreter = Interpreter(Parser(Lexer(text)))
    }
}