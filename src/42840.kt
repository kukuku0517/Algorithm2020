class Solution5 {

    val pattern1 = arrayOf(1, 2, 3, 4, 5)
    val pattern2 = arrayOf(2, 1, 2, 3, 2, 4, 2, 5)
    val pattern3 = arrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)

    fun solution(answers: IntArray): IntArray {
        var a1 = 0
        var a2 = 0
        var a3 = 0

        answers.forEachIndexed { index, i ->
            if (i == pattern1[index % pattern1.size]) a1++
            if (i == pattern2[index % pattern2.size]) a2++
            if (i == pattern3[index % pattern3.size]) a3++
        }

        val max = intArrayOf(a1, a2, a3).max()
        return intArrayOf(a1, a2, a3).mapIndexed { index, i -> if (i==max) index+1 else -1 }.filter { it>0 }.toIntArray()
    }
}