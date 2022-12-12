package com.elethien.practice

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

abstract class AdventBase {
    fun run() {
        println("\n=== === =========== === ===")
        println("=== === PROBLEM ${whichTest()} === ===")
        println("=== === =========== === ===")
        println("\nBegin Part1 ...")
        println(" >>> Part1 training:")
        //println("   [SOLUTION] " + run1(readInput("${whichTest()}-p1-sample")))
        println(" >>> Part1 problem:")
        println("   [SOLUTION] " + run1(readInput("${whichTest()}-p1")))
        println("\nBegin Part2 ...")
        println(" >>> Part2 training:")
        println("   [SOLUTION] " + run2(readInput("${whichTest()}-p1-sample")))
        println(" >>> Part2 problem:")
        println("   [SOLUTION] " + run2(readInput("${whichTest()}-p1")))
    }

    private fun whichTest() = this.javaClass.kotlin.simpleName

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

    fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

    private fun run1(input: List<String>): String =
        if (input.isEmpty()) " skipped"
        else part1(input)

    private fun run2(input: List<String>): String =
        if (input.isEmpty()) "   skipped"
        else part2(input)

    open fun part1(input: List<String>): String{
        return "NOT IMPLEMENTED"
    }
    open fun part2(input: List<String>): String{
        return "NOT IMPLEMENTED"
    }



}