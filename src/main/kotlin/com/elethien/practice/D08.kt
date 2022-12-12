package com.elethien.practice

class D08 : AdventBase() {
    override fun part1(input: List<String>): String {
        var max = 0
        var current = 0
        var data = mutableListOf<MutableList<Int>>()
        var visible = mutableListOf<MutableList<Boolean>>()
        var rows = 0
        var cols = 0
        input.forEach { line ->
            rows++
            var dataRow = mutableListOf<Int>()
            var visibleRow = mutableListOf<Boolean>()

            if (cols == 0) {
                cols = line.length
            }
            line.forEach { char ->
                dataRow.add(char.digitToInt())
                visibleRow.add(false)
            }
            data.add(dataRow)
            visible.add(visibleRow)
        }

        for (row in 0 until rows) {
            var actDataRow = data[row]
            var actVisibleRow = visible[row].toMutableList()
            for (col in 0 until cols) {
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                    actVisibleRow[col] = true
                } else {
                    var currentTree = actDataRow[col]
                    var visible = visibleHorizontal(
                        actDataRow = actDataRow,
                        currentTree = currentTree,
                        colFrom = 0,
                        colTo = col
                    )
                            ||
                            visibleHorizontal(
                                actDataRow = actDataRow,
                                currentTree = currentTree,
                                colFrom = col + 1,
                                colTo = cols
                            )
                            ||
                            visibleVertical(
                                currentTree = currentTree,
                                col = col,
                                rowFrom = 0,
                                rowTo = row,
                                data = data
                            )
                            ||
                            visibleVertical(
                                currentTree = currentTree,
                                col = col,
                                rowFrom = row + 1,
                                rowTo = rows, data = data
                            )
                    actVisibleRow[col] = visible
                }
            }
            visible[row] = actVisibleRow
        }
        // FINAL ITEM
        var list = visible.flatten()
        var result = list.count {
            it
        }
        return result.toString()
    }

    private fun visibleHorizontal(actDataRow: MutableList<Int>, currentTree: Int, colFrom: Int, colTo: Int): Boolean {
        var filtered = actDataRow.filterIndexed { index, item ->
            (index >= colFrom) && (index < colTo)
        }
        var visible = filtered.all { item ->
            item < currentTree
        }
        return visible
    }

    private fun visibleVertical(
        currentTree: Int,
        col: Int,
        rowFrom: Int,
        rowTo: Int,
        data: MutableList<MutableList<Int>>
    ): Boolean {
        for (checkingRow in rowFrom until rowTo) {
            if (currentTree <= data[checkingRow][col]) {
                return false
            }
        }
        return true
    }


    override fun part2(input: List<String>): String {
        var data = mutableListOf<MutableList<Int>>()
        var visible = mutableListOf<MutableList<Boolean>>()
        var rows = 0
        var cols = 0
        input.forEach { line ->
            rows++
            var dataRow = mutableListOf<Int>()
            var visibleRow = mutableListOf<Boolean>()

            if (cols == 0) {
                cols = line.length
            }
            line.forEach { char ->
                dataRow.add(char.digitToInt())
                visibleRow.add(false)
            }
            data.add(dataRow)
            visible.add(visibleRow)
        }

        var maxScore = 0L
        for (row in 1 until rows - 1) {
            var actDataRow = data[row]
            for (col in 1 until cols - 1) {
                var h1 = horizontalScore(
                    actDataRow = actDataRow,
                    colFrom = col - 1,
                    colTo = 0,
                    myHeight = actDataRow[col]
                )
                var h2 = horizontalScore(
                    actDataRow = actDataRow,
                    colFrom = col + 1,
                    colTo = cols - 1,
                    myHeight = actDataRow[col]
                )
                var v1 = verticalScore(
                    col = col,
                    rowFrom = row - 1,
                    rowTo = 0,
                    data = data,
                    myHeight = actDataRow[col]
                )
                var v2 =
                    verticalScore(
                        col = col,
                        rowFrom = row + 1,
                        rowTo = rows - 1,
                        data = data,
                        myHeight = actDataRow[col]
                    )
                var myScore = h1 * h2 * v1 * v2
                if (myScore > maxScore) {
                    println("SCORE: $myScore row $row col $col ($h1 * $h2 * $v1 * $v2) .. I am ${actDataRow[col]}")
                    maxScore = myScore
                }

            }
        }
        return maxScore.toString()
    }

    private fun horizontalScore(actDataRow: MutableList<Int>, colFrom: Int, colTo: Int, myHeight: Int): Long {
        var score = 0L
        //var height = 0
        var col = colFrom
        var end = false
        while (!end && checkCol(col, colFrom, colTo) ) {
            score++
            if (actDataRow[col] >= myHeight) {
                end = true
            } else {
                if (colTo > colFrom) {
                    col++
                } else {
                    col--
                }
            }
        }

        /*
        while ( checkCol(col, colFrom, colTo) && actDataRow[col] > height ) {
            score++
            height = actDataRow[col]
            if (colTo > colFrom ){
                col++
            } else {
                col --
            }
        }*/
        return score
    }

    private fun checkCol(col: Int, colFrom: Int, colTo: Int): Boolean =
        if (colFrom > colTo) {
            col >= colTo
        } else if (colFrom < colTo) {
            col <= colTo
        } else {
            col == colTo
        }

    private fun checkRow(row: Int, rowFrom: Int, rowTo: Int): Boolean =
        if (rowFrom > rowTo) {
            row >= rowTo
        } else if (rowFrom < rowTo) {
            row <= rowTo
        } else {
            row == rowTo
        }

    private fun verticalScore(
        col: Int,
        rowFrom: Int,
        rowTo: Int,
        data: MutableList<MutableList<Int>>,
        myHeight: Int
    ): Long {
        var score = 0L
        var height = 0
        var row = rowFrom
        var end = false
        while (!end && checkRow(row, rowFrom, rowTo) ) {
            score++
            if (data[row][col] >= myHeight) {
                end = true
            } else {
                if (rowTo > rowFrom) {
                    row++
                } else {
                    row--
                }
            }
        }
        /*
        while (checkRow(row , rowFrom, rowTo ) &&  data[row][col] >= height) {
            score++
            height = data[row][col]
            if (rowTo > rowFrom ){
                row++
            } else {
                row--
            }
        }*/
        return score

    }

}