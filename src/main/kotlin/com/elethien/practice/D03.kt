package com.elethien.practice


class D03 : AdventBase() {
    private val POINTS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override fun part1(input: List<String>): String {
        var total = 0
        var lines = 0
        input.forEach { line ->
            if (!line.trim().isEmpty()) {
                val parts = line.chunked(line.length / 2)
                val x = parts[0]
                val y = parts[1]
                val misplacedItem = x.toSet().intersect(y.toSet())
                val points = POINTS.indexOf(misplacedItem.first()) +1
                total += points
                lines++
            }
        }
        return total.toString()
    }

    override fun part2(input: List<String>): String {
        var total = 0
        var num = 0
        //var groupSet = mutableSetOf<String>()
        var common = emptySet<Char>()
        input.forEach { line ->
            if (!line.trim().isEmpty()) {
                if (num %3 == 0) {
                    common = line.toSet()
                } else {
                    common = common.toSet().intersect(line.toSet())
                }
                if (num %3 == 2){
                    total += POINTS.indexOf(common.first()) +1
                }
                num++
            }
        }
        return total.toString()
    }


}