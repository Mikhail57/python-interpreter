package ru.mustakimov.pascal

import ru.mustakimov.pascal.exception.UndefinedVariableError
import ru.mustakimov.pascal.node.*
import ru.mustakimov.pascal.node.Number
import ru.mustakimov.pascal.token.TokenType

class Interpreter internal constructor(
    private val parser: Parser
) : NodeVisitor {
    val variables: MutableMap<String, Float> = mutableMapOf()

    fun interpret() {
        val tree = parser.parse()
        visit(tree)
    }

    override fun visit(node: Node): Any? {
        return when (node) {
            is BinOp -> visitBinOp(node)
            is Number -> visitNumber(node)
            is UnaryOp -> visitUnaryOp(node)
            is Block -> visitBlock(node)
            is Assign -> visitAssign(node)
            is Var -> visitVar(node)
            is NoOp -> {
            }
            else -> throw UnsupportedOperationException()
        }
    }

    private fun visitVar(node: Var): Any {
        val variableName = node.value
        return variables[variableName] ?: throw UndefinedVariableError(variableName)
    }

    private fun visitAssign(node: Assign): Any? {
        val variableName = (node.left as Var).value
        variables[variableName] = visit(node.right) as Float
        return null
    }

    private fun visitBlock(node: Block): Any? {
        for (child in node.children) {
            visit(child)
        }
        return null
    }

    private fun visitUnaryOp(node: UnaryOp): Float {
        return when (node.token.type) {
            TokenType.MINUS -> -(visit(node.expr) as Float)
            TokenType.PLUS -> visit(node.expr) as Float
            else -> throw UnsupportedOperationException()
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