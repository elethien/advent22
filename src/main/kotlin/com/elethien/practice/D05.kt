package com.elethien.practice
class D05 : AdventBase() {

    override fun part1(input: List<String>): String {
        var total = 0
        var containers = true
        var disposition = mutableListOf<String>()
        var stacks = mutableListOf<ArrayDeque<Char>>()
        for (i in 1..10){
            stacks.add( ArrayDeque<Char>())
        }
        input.forEachIndexed { i, line ->
            if (!line.trim().isEmpty()) {
                if (containers){
                    disposition.add(line)
                } else {
                    applyMove(stacks, line)
                }
            } else{
                containers  = false
                var indexLine = disposition.get(disposition.size -1)
                indexLine.forEachIndexed { index, char ->
                    if (char != ' '){
                        val pos = char.digitToInt()
                        val stack = stacks[pos]
                        val initial = disposition.size -2
                        for (i in initial downTo  0 ){
                            var current = disposition[i]
                            if (current.length > index && current[index] != ' ') {
                                stack.addLast(current[index])
                            }
                        }
                    }
                }
            }
        }
        val solution = getSolution(stacks)
        return  solution
    }

    private fun getSolution(stacks: MutableList<ArrayDeque<Char>>): String {
        var sol =""
        for (i in 1 until stacks.size){
            val stack = stacks[i]
            if (stack.size > 0){
                sol += stack[stack.size -1]
            }
        }
        return sol
    }

    private fun applyMove(stacks: MutableList<ArrayDeque<Char>>, line: String) {
        val moves = line.split(' ')
        val number = moves[1].toInt()
        val from = moves[3].toInt()
        val to = moves[5].toInt()
        for(i in 1..number){
            val item = stacks[from].removeLast()
            stacks[to].addLast(item)
        }
    }

    override fun part2(input: List<String>): String {
        var total = 0
        var containers = true
        var disposition = mutableListOf<String>()
        var stacks = mutableListOf<ArrayDeque<Char>>()
        for (i in 1..10){
            stacks.add( ArrayDeque<Char>())
        }
        input.forEachIndexed { i, line ->
            if (!line.trim().isEmpty()) {
                //val elfs = line.split(',')
                if (containers){
                    disposition.add(line)
                } else {
                    applyMove2(stacks, line)
                }
            } else{
                containers  = false
                var indexLine = disposition.get(disposition.size -1)
                indexLine.forEachIndexed { index, char ->
                    if (char != ' '){
                        val pos = char.digitToInt()
                        val stack = stacks[pos]
                        val initial = disposition.size -2
                        for (i in initial downTo  0 ){
                            var current = disposition[i]
                            if (current.length > index && current[index] != ' ') {
                                stack.addLast(current[index])
                            }
                        }
                    }
                }
            }
        }
        val solution = getSolution(stacks)
        return  solution
    }

    private fun applyMove2(stacks: MutableList<ArrayDeque<Char>>, line: String) {
        val moves = line.split(' ')
        val number = moves[1].toInt()
        val from = moves[3].toInt()
        val to = moves[5].toInt()
        val tempStack = ArrayDeque<Char>()
        for(i in 1..number){
            val item = stacks[from].removeLast()
            tempStack.addLast(item)
            //stacks[to].addLast(item)
        }
        for(i in 1..number){
           stacks[to].addLast(tempStack.removeLast())
        }
    }
}