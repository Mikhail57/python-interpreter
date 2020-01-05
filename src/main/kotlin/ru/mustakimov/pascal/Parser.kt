package ru.mustakimov.pascal

import ru.mustakimov.pascal.exception.UnexpectedTokenException
import ru.mustakimov.pascal.node.BinOp
import ru.mustakimov.pascal.node.Node
import ru.mustakimov.pascal.node.Number
import ru.mustakimov.pascal.node.UnaryOp
import ru.mustakimov.pascal.token.Token
import ru.mustakimov.pascal.token.TokenType

class Parser(
    private val lexer: Lexer
) {
    private var currentToken: Token = lexer.nextToken()

    fun parse(): Node {
        return expression()
    }

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
            else -> throw UnexpectedTokenException("Unexpected token")
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

    private fun expression(): Node {
        var node = factor()

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