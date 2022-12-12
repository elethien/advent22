package com.elethien.practice

class D10 : AdventBase() {

    val OFFSET = 2
    val TIMES = intArrayOf(20, 60, 100, 140, 180, 220)

    override fun part1(input: List<String>): String {

        val timeResults =  mutableMapOf<Int, Int>()
        var value = 1
        var timeIndex = 0
        var position = 1
        var skip = false
        var process = true
        var inputIndex = 0
        val buffer =  mutableMapOf<Int, Int>()

        while (process){
            if (buffer[position] != null){
                value += buffer[position] ?: 0
            }
            println ("*** POS: $position  VAL:$value")
            if (timeIndex < TIMES.size && position == TIMES[timeIndex]){
                timeResults[TIMES[timeIndex]] = value
                timeIndex ++
            }
            if (skip == true){
                skip = false;
                position ++
            } else {
                var line =  if (inputIndex < input.size) {
                    input[inputIndex++]

                } else {
                    "ending"
                }
                println(line)
                if (line.startsWith("addx")) {
                    //actualVal += line.split(" ")[1].toInt()
                    buffer.put(position + OFFSET,  line.split(" ")[1].toInt())
                    position++
                    skip = true
                } else {
                    // noop
                    position++
                    skip = false
                }
            }
            if (inputIndex == input.size && skip == false){
                process = false
            }
        }
        var result = 0
        TIMES.forEach {
            println ("CYCLE $it SIGNAL ${timeResults[it]}  for score ${it * (timeResults[it] ?: 0)}")
            result += (timeResults[it] ?: 0) * it
        }

        println ("------WITHOUT COMPLETING $result")

        while (timeIndex < TIMES.size){
            timeResults[TIMES[timeIndex]] = value
            timeIndex++
        }
        result = 0
        TIMES.forEach {
            println ("CYCLE $it SIGNAL ${timeResults[it]}  for score ${it * (timeResults[it] ?: 0)}")
            result += (timeResults[it] ?: 0) * it
        }
        println ("---------COMPLETING $result")



        return result.toString()
    }







    override fun part2(input: List<String>): String {
        return ""
    }
}