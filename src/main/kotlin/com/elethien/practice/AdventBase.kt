package com.elethien.practice

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

abstract class AdventBase {
    fun run() {
        println("\n\nBegin Part1 ...")
        println(" >>> Part1 training:")
        println(part1(readInput("${whichTest()}-p1-sample")))
        println(" >>> Part1 problem:")
        println(part1(readInput("${whichTest()}-p1")))

        println("\n\nBegin Part2 ...")
        println(" >>> Part2 training:")
        println(part2(readInput("${whichTest()}-p2-sample")))
        println(" >>> Part2 problem:")
        println(part2(readInput("${whichTest()}-p2")))
    }

    private fun whichTest() = this.javaClass.kotlin.simpleName

    private fun readInput(name: String) : List<String> = kotlin.runCatching {
        File("src/main/resources","${name}.txt").readLines().also{
            if (it.isEmpty())
                println("  Empty file: $name")
            else
                println("  ${it.size} lines read")
        }
    }.fold(
        { it },
        {
            println("  ERROR READ FILE: $name, cause: ${it.message}")
            emptyList()
        }
    )


    fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)


    open fun part1(input: List<String>): String{
        return "NOT IMPLEMENTED(BASE)"
    }
    open fun part2(input: List<String>): String{
        return "NOT IMPLEMENTED(BASE)"
    }
}