class Solution12 {
    val courseMap = mutableMapOf<String, Int>()
    val countMap = mutableMapOf<Int, Int>()

    fun solution(orders: Array<String>, courses: IntArray): Array<String> {
        orders.forEach { order ->
            courses.forEach { course ->
                take(order.toCharArray().sorted().joinToString(""), "", 0, course)
            }
        }

        val filtered = courseMap.filter { it.value > 1 && it.value >= countMap[it.key.length]!! }

        return filtered.keys.toTypedArray().sortedArray()
    }

    fun take(original: String, str: String, index: Int, count: Int) {
        if (count == 0) {
            val c = courseMap.getOrPut(str) { 0 } + 1
            courseMap[str] = c
//            println("$str $c")
            if (countMap.getOrPut(str.length) { Int.MIN_VALUE } < c) {
                countMap[str.length] = c
            }
            return
        }
        (index until original.length).forEach {
            take(original, str + original[it], it + 1, count - 1)
        }
    }
//
//    //65 - 90
//    //ZYX...CBA
//    fun orderToBitmask(order: String): Int {
//        var bitmask = 0
//        order.forEach {
//            bitmask = bitmask or (1 shl (it.toInt() - 65))
//        }
//        return bitmask
//    }
//
//    fun compareBitMask(result: Int): String {
//        var count = 0
//        var r = result
//        var chars = ""
//        while (r > 0) {
//            if (r and 1 == 1) {
//                chars += (count + 65).toChar()
//            }
//            count++
//            r = r shr 1
//        }
//        return chars
//    }

}