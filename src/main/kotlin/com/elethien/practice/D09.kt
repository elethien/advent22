package com.elethien.practice

import java.lang.StrictMath.abs

class D09 : AdventBase() {

    var visited: MutableMap<Int, MutableMap<Int, Int>> = mutableMapOf()

    data class Position constructor(var col: Int, var row: Int) {}

    override fun part1(input: List<String>): String {
        visited = mutableMapOf()
        val moveList: List<Char> = getMoves(input)
        var head = Position(0, 0)
        var tail = Position(0, 0)
        visited.markVisited(tail)
        moveList.forEach { move ->
            moveHead(head, move)
            if (!head.isAdyacent(tail)) {
                moveTail(head, tail)
                visited.markVisited(tail)
            }
            //println("$move  HEAD: ${head.row},${head.col} ... tail:${tail.row},${tail.col}")
        }
        //paint(visited)
        val solution = visited.values.map { it.values }
        val sol2 = solution.flatten()
        return sol2.count().toString()
    }

    private fun paint(visited: MutableMap<Int, MutableMap<Int, Int>>) {
        val minRow = visited.keys.min()
        val maxRow = visited.keys.max()

        var minCol = 100000
        var maxCol = -100000
        visited.values.forEach {
            var min = it.keys.min()
            var max = it.keys.max()
            if (min < minCol) minCol = min
            if (max > maxCol) maxCol = max
        }
    }

    private fun moveHead(head: D09.Position, move: Char) =
        when (move) {
            'U' -> head.row++
            'D' -> head.row--
            'L' -> head.col--
            'R' -> head.col++
            else -> println("UNEXPCTED NO MOVE")
        }


    private fun moveTail(head: Position, tail: Position) {
        tail.col = tail.col +
                (1.takeIf { head.col > tail.col } ?: 0) +
                ((-1).takeIf { head.col < tail.col } ?: 0)
        tail.row = tail.row + (1.takeIf { head.row > tail.row } ?: 0) + ((-1).takeIf { head.row < tail.row } ?: 0)
    }

    private fun getMoves(input: List<String>): List<Char> {
        var moves = mutableListOf<Char>()
        input.forEach { line ->
            var direction: Char = line[0]
            var num = line.split(" ")[1].toInt()
            for (i in 0 until num) {
                moves.add(direction)
            }
        }
        return moves
    }


    override fun part2(input: List<String>): String {
        visited = mutableMapOf()
        val moveList: List<Char> = getMoves(input)
        var head = Position(0, 0)
        var tails = listOf(
            Position(0, 0), //1
            Position(0, 0),
            Position(0, 0),
            Position(0, 0),
            Position(0, 0), //5
            Position(0, 0),
            Position(0, 0),
            Position(0, 0),
            Position(0, 0), // TAIL item 9, index 8
        )
        visited.markVisited(tails[8])
        moveList.forEach { move ->
            moveHead(head, move)
            var leader = head
            tails.forEachIndexed{ index, current ->
                if (!leader.isAdyacent(current)) {
                    moveTail(leader, current)
                    if (index == 8) {
                        visited.markVisited(current)
                    }
                }
                leader =  current
            }

        }
        //paint(visited)
        val solution = visited.values.map { it.values }
        val sol2 = solution.flatten()
        return sol2.count().toString()
    }
}


private fun MutableMap<Int, MutableMap<Int, Int>>.markVisited(tail: D09.Position) {
    var row = this.get(tail.row)
    if (row == null) {
        row = mutableMapOf<Int, Int>()
        this.put(tail.row, row)
    }
    var count = row[tail.col]
    if (count == null) {
        row.put(tail.col, 1)
    } else {
        count++
        row.put(tail.col, count)
    }
}

private fun D09.Position.isAdyacent(tail: D09.Position): Boolean =
    if (abs(this.col - tail.col) > 1) false
    else abs(this.row - tail.row) <= 1