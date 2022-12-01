package com.elethien.practice

class D01 : AdventBase() {
    override fun part1(input: List<String>): String {
        var max = 0
        var current = 0
        input.forEach{line ->
            if(line.trim().isEmpty()) {
                if (current > max){
                    max = current
                }
                current = 0
            } else {
                current += line.trim().toInt()
            }
        }
        // FINAL ITEM
        if (current > max){
            max = current
        }
        return max.toString()
    }


    override fun part2(input: List<String>): String {
        var top3 = mutableListOf(0,0,0) // SORTED Big to low
        var current = 0
        input.forEach{line ->
            if(line.trim().isEmpty()) {
                if (current > getMin(top3)){
                    addToTop3(top3,current)
                }
                current = 0
            } else {
                current += line.trim().toInt()
            }
        }
        //final ITEM
        if (current > getMin(top3)){
            addToTop3(top3,current)
        }
        return top3.sum().toString()
    }

    private fun getMin(top3: List<Int>): Int = top3[2]


    private fun addToTop3(top3: MutableList<Int>, current: Int) {
        var added = false
        var pos = 0
        while (! added && pos < top3.size) {
            if (current > top3[pos]){
                top3.add(pos, current)
                added = true
            }
            pos++
        }
        top3.removeAt(3)
    }
}