package ru.mustakimov.pascal.node

class NoOp : Node {
    override fun equals(other: Any?): Boolean {
        return other is NoOp
    }
}