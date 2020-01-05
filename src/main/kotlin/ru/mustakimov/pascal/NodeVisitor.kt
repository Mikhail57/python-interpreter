package ru.mustakimov.pascal

import ru.mustakimov.pascal.node.Node

interface NodeVisitor {
    fun visit(node: Node): Any
}