package com.elethien.practice

import java.io.File

fun main() {
    val input = readInput("D10-p1")
    var out = ""
    input.forEach{
        if (it.startsWith("addx")){
            out += it.split(" ")[1] + "\n"
        } else {
            out +="\n"
        }
    }

    print(out)


}

private fun readInput(name: String) : List<String> = kotlin.runCatching {
    File("src/main/resources","${name}.txt").readLines().also{
        if (it.isEmpty())
            println("   [fileread] Omitting empty file: $name.txt")
    }
}.fold(
    { it },
    {
        println("   [fileread] ERROR READ FILE: $name.txt, cause: ${it.message} (!!)")
        emptyList()
    }
)