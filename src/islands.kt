class Solution6 {

    var touched: List<MutableList<Boolean>> = listOf()
    var intGrid: Array<Array<Int>> = arrayOf()

    fun numIslands(grid: Array<CharArray>): Int {
        var count = 0
        if (grid.isEmpty())
            return 0
        intGrid = Array(grid.first().size) { Array(grid.size) { 0 } }
        grid.forEachIndexed { j, chars ->
            chars.forEachIndexed { i, c ->
                intGrid[i][j] = if (c == '1') 1 else 0
            }
        }
        touched = intGrid.map { it.map { false }.toMutableList() }
        intGrid.forEachIndexed a@{ i, list ->
            list.forEachIndexed b@{ j, value ->
                if (move(i, j)) {
                    count++
                }
            }
        }
        return count;
    }

    fun move(i: Int, j: Int): Boolean {
        if (i < 0 || i >= touched.size || j < 0 || j >= touched.first().size) return false
        if (touched[i][j]) return false
//        println("move $i,$j")
        touched[i][j] = true
        if (intGrid[i][j] == 0) {
//            println("water $i,$j")
            touched[i][j] = true
            return false
        }

        if (i > 0) {
            move(i - 1, j)
        }
        if (i < touched.size) {
            move(i + 1, j)
        }
        if (j > 0) {
            move(i, j - 1)
        }
        if (j < touched.first().size) {
            move(i, j + 1)
        }
        return true
    }
}