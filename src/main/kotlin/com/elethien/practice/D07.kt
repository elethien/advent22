package com.elethien.practice
class D07 : AdventBase() {

    override fun part1(input: List<String>): String {
        var LIMIT = 100000
        var root = Directory(
            name = "/",
           // path = "/",
            root = true,
            parent =  null,
            childs =mutableListOf(),
            myFilesSize = 0,
            myDirsSize = 0,
            myTotalSize = 0
        )
        val filesystem : MutableList<Directory> = mutableListOf(
            root
        )
        var currentDir: Directory = root
        var currentPath = mutableListOf<String>("/")
        input.forEachIndexed { i, line ->
            if (line.startsWith("$ cd /")){
                currentDir = root
                while(currentPath.size > 1) { currentPath.removeLast()}
            }
            else if (line.startsWith("$ cd ..")){
                currentDir = currentDir.parent!!
                currentPath.removeLast()
            }
            else if (line.startsWith("$ cd ")){
                var folderName = line.substring(5, line.length)
                currentDir = addChildDirectory(currentDir, currentPath, folderName, filesystem)
                currentPath.add(folderName)
            }
            else if (line.startsWith("$ ls")){
                // NO action
            } else {
                var parts = line.split(" ")
                if (parts[0] == "dir") {
                    var dirName = parts[1]
                    addChildDirectory(currentDir, currentPath,  dirName, filesystem)

                } else {
                    currentDir.myFilesSize += parts[0].toLong()
                    currentDir.myTotalSize += parts[0].toLong()
                    updateParentSize(currentDir)
                }
            }
        }
        var directories  = filesystem.filter{it.myTotalSize <= LIMIT}
        var size = directories.sumOf{ it.myTotalSize}
        return size.toString()
    }

    private fun updateParentSize(currentDir: D07.Directory) {
        var actual = currentDir
        while (!actual.root) {
            actual = actual.parent!!
            val newSize = actual.childs.sumOf { it -> it.myTotalSize }
            actual.myDirsSize =  newSize
            actual.myTotalSize = actual.myDirsSize + actual.myFilesSize
        }
    }

    private fun addChildDirectory(
        currentDir: Directory,
        currentPath: MutableList<String>,
        newDirName: String,
        filesystem: MutableList<Directory>
    ) :Directory{
        val existing = currentDir.childs.firstOrNull{ it.name == newDirName}
        if (existing != null) {
            return existing
        }
        var newDir = Directory(
            name = newDirName,
           // path = buildPath(currentPath, newDirName),
            root = false,
            parent = currentDir,
            childs  = mutableListOf(),
            myFilesSize = 0,
            myDirsSize = 0,
            myTotalSize = 0
        )
        currentDir.childs.add(newDir)
        filesystem.add(newDir)
        return newDir
    }

    private fun buildPath(currentPath: MutableList<String>, dirName: String): String {
        var s =  currentPath[0]
        var nonroot = currentPath.filterIndexed{index, item -> index != 0 }.joinToString("/")
        if (nonroot.length > 0) {
            s += nonroot + "/"
        }
        s+= dirName+ "/"
        return s
    }

    override fun part2(input: List<String>): String {

        var root = Directory(
            name = "/",
           // path = "/",
            root = true,
            parent =  null,
            childs =mutableListOf(),
            myFilesSize = 0,
            myDirsSize = 0,
            myTotalSize = 0
        )
        val filesystem : MutableList<Directory> = mutableListOf(
            root
        )
        var currentDir: Directory = root
        var currentPath = mutableListOf<String>("/")
        input.forEachIndexed { i, line ->
            if (line.startsWith("$ cd /")){
                currentDir = root
                while(currentPath.size > 1) { currentPath.removeLast()}
            }
            else if (line.startsWith("$ cd ..")){
                //print (line  + " >>>> "  + currentDir.name)
                currentDir = currentDir.parent!!
                //println ( "   RESULT: " +line  + " >>>> "  + currentDir.name)
                currentPath.removeLast()
            }
            else if (line.startsWith("$ cd ")){
                //print (line  + " >>>> "  + currentDir.name)
                var folderName = line.substring(5, line.length)
                var added = addChildDirectory(currentDir, currentPath, folderName, filesystem)
                currentDir = added
                //println ( "   RESULT: " + line  + " >>>> "  + currentDir.name)
                currentPath.add(folderName)
            }
            else if (line.startsWith("$ ls")){
                // NO action
            } else {
                var parts = line.split(" ")
                if (parts[0] == "dir") {
                    var dirName = parts[1]
                    addChildDirectory(currentDir, currentPath,  dirName, filesystem)

                } else {
                    currentDir.myFilesSize += parts[0].toLong()
                    currentDir.myTotalSize += parts[0].toLong()
                    updateParentSize(currentDir)
                }
            }
        }
        var DISKSPACE = 70000000
        var maxOcccup = 40000000
        var totalSize = filesystem[0].myTotalSize
        var needToFree = totalSize - maxOcccup

        var directories = filesystem.filter {it.myTotalSize >= needToFree }.sortedBy { it.myTotalSize }

        /*
        var directories  = filesystem.sortedBy { it.myTotalSize }
        directories.forEach{
            var path = it.name
            var act = it
            while (!act.root) {
                act = act.parent!!
                path = act.name + "/" + path
            }

            println(path +  "  TOTAL: "+it.myTotalSize +  ("  F="+it.myFilesSize+ "  dirs="+it.myDirsSize) )
        }
        */




        return directories[0].myTotalSize.toString()
    }

    class Directory (
        var name: String,
        //var path: String,
        var root:Boolean = false,
        var parent: Directory? = null,
        var childs: MutableList<Directory> = mutableListOf(),
        var myFilesSize: Long = 0,
        var myDirsSize: Long = 0,
        var myTotalSize: Long = 0
    ){
    }

}