package com.elethien.practice

class D02 : AdventBase() {
    override fun part1(input: List<String>): String {
        var total = 0
        input.forEach { line ->
            if (!line.trim().isEmpty()) {
                var moves = line.split(' ')
                var mine = moves[1]

                val points = getResultPoints(line) + getMyShapePoints(mine)
                total += points
            }
        }
        return total.toString()
    }

    //OPONENT A for Rock, B for Paper, and C for Scissors.
    //ME      X for Rock, Y for Paper, and Z for Scissors
    private fun getResultPoints(line: String): Int = when (line) {
        // I  win
        "A Y", "B Z", "C X" -> 6

        // Draw
        "A X", "B Y", "C Z" -> 3

        // I lose
        else -> 0
    }
    private fun getMyShapePoints(myMove: String): Int = when (myMove) {
        "X" -> 1
        "Y" -> 2
        "Z" -> 3
        else -> 0
    }

    override fun part2(input: List<String>): String {
        var total = 0
        input.forEach { line ->
            if (!line.trim().isEmpty()) {
                var correctedPlay = getMove(line)
                var moves = correctedPlay.split(' ')
                var mine = moves[1]
                val points = getResultPoints(correctedPlay) + getMyShapePoints(mine)
                total += points
            }
        }
        return total.toString()
    }

    //OPONENT A for Rock, B for Paper, and C for Scissors.
    //X lose, Y draw, Z,WINN

    //ME      X for Rock, Y for Paper, and Z for Scissors
    private fun getMove(line: String): String = when (line) {
        "A X" -> "A Z"
        "A Y" -> "A X"
        "A Z" -> "A Y"
        "B X" -> "B X"
        "B Y" -> "B Y"
        "B Z" -> "B Z"
        "C X" -> "C Y"
        "C Y" -> "C Z"
        "C Z" -> "C X"

        else -> throw Error()
    }
}