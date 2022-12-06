package com.elethien.practice
class D06 : AdventBase() {

    override fun part1(input: List<String>): String {
        val SIZE = 4
        return solve(input[0], SIZE)
    }

    private fun solve(line: String, patternSize: Int): String{
        var solution:Int? = null
        var index = patternSize
        while (solution == null){
            if (line.substring(index - patternSize, index).toSet().size == patternSize){
                solution = index
            }
            index++
        }
        return solution.toString()
    }

    override fun part2(input: List<String>): String {
        val SIZE = 14
        return solve(input[0], SIZE)
    }

    /*
    SIN OPTMIZAR
     override fun part2(input: List<String>): String {
        var total = 0
        input.forEachIndexed { i, line ->
            val buffer = mutableListOf<Char>()
            var solved = false
            var index = 0
            while (! solved){
                val char = line[index]
                buffer.add(char)
                if (index >= 13) {
                    if (buffer.toSet().size == 14) {
                        println("LINE $i SOLUTION=${(index + 1)}")
                        solved = true
                    }
                }
                if (buffer.size >= 14) {
                    buffer.removeAt(0)
                }
                index++
            }
        }
        //val solution = getSolution(stacks)
        return  ""
    }

     */

}