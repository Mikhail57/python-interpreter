package ru.mustakimov.pascal.exception

class UndefinedVariableError(val variableName: String) : Error() {
    override fun toString(): String {
        return "Undefined variable $variableName used"
    }
}