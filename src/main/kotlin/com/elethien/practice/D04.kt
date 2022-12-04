package com.elethien.practice
class D04 : AdventBase() {
    override fun part1(input: List<String>): String {
        var total = 0
        var lines = 0
        input.forEachIndexed { i, line ->
            if (!line.trim().isEmpty()) {
                val elfs = line.split(',')
                if (insideAofB(elfs[0], elfs[1]) || insideAofB(elfs[1], elfs[0]) ){
                    total ++
                }
            }
        }
        return total.toString()
    }

    override fun part2(input: List<String>): String {
        var total = 0
        var num = 0
        var common = emptySet<Char>()
        input.forEachIndexed { i, line ->
            if (!line.trim().isEmpty()) {
                val elfs = line.split(',')
                if (overlap(elfs[0], elfs[1])  ){
                    total ++
                }
            }
        }
        return total.toString()
    }

    private fun insideAofB(elfA: String, elfB: String): Boolean {
        val startA = elfA.split('-')[0].toInt()
        val endA = elfA.split('-')[1].toInt()

        val startB = elfB.split('-')[0].toInt()
        val endB = elfB.split('-')[1].toInt()

        return (startA >= startB) && (endA <= endB)
    }

    private fun overlap(elfA: String, elfB: String): Boolean {
        val startA = elfA.split('-')[0].toInt()
        val endA = elfA.split('-')[1].toInt()

        val startB = elfB.split('-')[0].toInt()
        val endB = elfB.split('-')[1].toInt()

        return (startA <= endB) && (endA >= startB)
    }
}