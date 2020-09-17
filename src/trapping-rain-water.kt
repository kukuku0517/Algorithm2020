class Solution {
    lateinit var bitHeight: Array<Array<Int>>
    fun trap(height: IntArray): Int {
        var result = 0
        val x = height.size
        val y = height.max()?:0
        bitHeight = Array(y) { Array(x) { 0 } }

        height.forEachIndexed { index, i ->
            (0 until i).forEach {
                bitHeight[it][index] = 1
            }
        }

//        println(bitHeight.map { it.toList() }.toList())

        bitHeight.forEachIndexed { index, floor ->

            var trapped = false
            var cached = 0
            floor.forEach {
                if (it == 1) {
                    trapped = true
                    if (cached > 0) {
                        result += cached
                        cached = 0
                    }
                } else {
                    if (trapped) cached++
                }
            }

        }
        return result
    }
}