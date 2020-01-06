package ru.mustakimov.pascal

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Simple pascal interpreter.\nUSAGE: <EXECUTABLE> <FILENAME>")
        println("Where:\n\t<EXECUTABLE> - this file (often java -jar pascal-interpreter.jar)")
        println("\t<FILENAME> - file to be interpreted")
        exitProcess(1)
    }

    val filename = args.first()
    val file = File(filename)
    if (!file.exists() || !file.isFile) {
        println("You should specify correct file. Cannot open file")
    }
    val content = BufferedReader(FileReader(file)).readText()
    val interpreter = Interpreter.newInstance(content)
    interpreter.interpret()
    println("Used variables:")
    interpreter.variables.forEach {
        println("\t${it.key} = ${it.value}")
    }
}