package com.elethien.practice
class D04 : AdventBase() {
    override fun part1(input: List<String>): String {
        var total = 0
        var lines = 0
        input.forEach { line ->
            if (!line.trim().isEmpty()) {

            }
        }
        return total.toString()
    }

    override fun part2(input: List<String>): String {
        var total = 0
        var num = 0
        var common = emptySet<Char>()
        input.forEach { line ->
            if (!line.trim().isEmpty()) {
                num++
            }
        }
        return total.toString()
    }
}