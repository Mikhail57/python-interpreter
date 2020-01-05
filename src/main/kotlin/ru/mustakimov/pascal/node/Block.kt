package ru.mustakimov.pascal.node

data class Block(
    val children: MutableList<Node>
) : Node