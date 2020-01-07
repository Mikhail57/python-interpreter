package ru.mustakimov.pascal

import ru.mustakimov.pascal.exception.UnexpectedTokenException
import ru.mustakimov.pascal.node.*
import ru.mustakimov.pascal.token.Token
import ru.mustakimov.pascal.token.TokenType

internal class Parser(
    private val lexer: Lexer
) {
    private var currentToken: Token = lexer.nextToken()

    fun parse(): Node {
        val node = program()
        if (currentToken.type != TokenType.EOF)
            throw UnexpectedTokenException("After dot there should be no meaning things")
        return node
    }

    private fun program(): Node {
        val node = complexStatement()
        eat(TokenType.DOT)
        return node
    }

    private fun complexStatement(): Node {
        eat(TokenType.BEGIN)
        val nodes = statementList()
        eat(TokenType.END)
        return Block(nodes)
    }

    private fun statementList(): List<Node> {
        val node = statement()
        val result = mutableListOf(node)
        while (currentToken.type == TokenType.SEMI) {
            eat(TokenType.SEMI)
            result += statement()
        }
        if (currentToken.type == TokenType.ID) {
            throw UnexpectedTokenException("Unexpected variable ${currentToken.value}")
        }
        return result
    }

    private fun statement(): Node {
        return when (currentToken.type) {
            TokenType.BEGIN -> complexStatement()
            TokenType.ID -> assigmentStatement()
            else -> empty()
        }
    }

    private fun assigmentStatement(): Node {
        val left = variable()
        eat(TokenType.ASSIGN)
        val right = expression()
        return Assign(left, right)
    }

    private fun variable(): Node = Var(currentToken).also { eat(TokenType.ID) }

    private fun empty(): Node = NoOp()

    private fun eat(tokenType: TokenType) {
        if (currentToken.type == tokenType) {
            currentToken = lexer.nextToken()
        } else {
            throw UnexpectedTokenException("There should be $tokenType, but ${currentToken.type} provided")
        }
    }

    private fun factor(): Node {
        val token = currentToken
        return when (token.type) {
            TokenType.PLUS -> {
                eat(TokenType.PLUS)
                UnaryOp(token, factor())
            }
            TokenType.MINUS -> {
                eat(TokenType.MINUS)
                UnaryOp(token, factor())
            }
            TokenType.INTEGER -> {
                eat(TokenType.INTEGER)
                Number(token)
            }
            TokenType.LPAREN -> {
                eat(TokenType.LPAREN)
                val node = expression()
                eat(TokenType.RPAREN)
                node
            }
            else -> variable()
        }
    }

    private fun term(): Node {
        var node = factor()

        while (currentToken.type == TokenType.MUL || currentToken.type == TokenType.DIV) {
            val token = currentToken
            when (token.type) {
                TokenType.MUL -> eat(TokenType.MUL)
                TokenType.DIV -> eat(TokenType.DIV)
                else -> {
                }
            }
            node = BinOp(left = node, op = token, right = factor())
        }

        return node
    }

    fun expression(): Node {
        var node = term()

        while (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
            val token = currentToken
            when (token.type) {
                TokenType.PLUS -> eat(TokenType.PLUS)
                TokenType.MINUS -> eat(TokenType.MINUS)
                else -> {
                }
            }
            node = BinOp(left = node, op = token, right = term())
        }

        return node
    }
}