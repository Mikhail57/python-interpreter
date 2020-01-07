package ru.mustakimov.pascal.node

/**
 * AST node representation for no-operation. Used by [Block]
 *
 * Overrides equals and hashcode to handle equality properly and follow hashcode contract
 */
class NoOp : Node {
    override fun equals(other: Any?): Boolean {
        return other is NoOp
    }

    override fun hashCode(): Int {
        return javaClass.canonicalName.hashCode()
    }
}