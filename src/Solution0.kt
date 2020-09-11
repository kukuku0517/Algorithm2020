class Solution0 {
    val MAX_COUNT = 8
    val resultMap = mutableMapOf<Double, Int>()
    val countList = Array<MutableList<Double>>(MAX_COUNT + 1) {
        mutableListOf()
    }

    var N = 0
    var number = 0.0
    var minCount = Int.MAX_VALUE

    fun updateMap(value: Double, count: Int): Boolean {
        return if (resultMap.getOrDefault(value, Int.MAX_VALUE) > count) {
            resultMap[value] = count
            true
        } else
            false

    }

    fun seq(n: Int, count:Int): Int {
        var a = 0
        (0 until count).forEach {
            a = a * 10 + n
        }
        return a
    }

    fun solution(N: Int, number: Int): Int {
        this.N = N
        this.number = number.toDouble()

        (1..8).forEach {
            val seq = seq(N, it).toDouble()
            if (seq == number.toDouble()){
                return it
            }
            countList[it].add(seq(N, it).toDouble())
        }

        (2..MAX_COUNT).forEach {
            if (minCount!= Int.MAX_VALUE) return@forEach
            go(it)
        }

        return if (minCount == Int.MAX_VALUE) -1 else minCount
    }

    private fun go(nextIndex: Int) {
        println("index $nextIndex")
        (1 until nextIndex).forEach {
            if (it> nextIndex-it) return@forEach
            println("\t $it + ${nextIndex-it}")
            val a = countList[it]
            val b = countList[nextIndex - it]
            a.forEach { aa ->
                b.forEach { bb ->
                    Func.values().forEach aa@{
                        when (it) {
                            Func.ADD -> {
                                val result = aa + bb
                                if (minCount > nextIndex && result == number) {
                                    minCount = nextIndex
                                    return
                                }
                                if (updateMap(result, nextIndex)) {
                                    countList[nextIndex].add(result)
                                }
                            }
                            Func.SUB -> {
                                val result = aa - bb
                                val result2 = bb+ aa
                                if (minCount > nextIndex && (result == number || result2 == number)) {
                                    minCount = nextIndex
                                    return
                                }
                                if (updateMap(result, nextIndex)) {
                                    countList[nextIndex].add(result)
                                }
                                if (updateMap(result2, nextIndex)) {
                                    countList[nextIndex].add(result2)
                                }
                            }
                            Func.MUL -> {
                                val result = aa * bb
                                if (minCount > nextIndex && result == number) {
                                    minCount = nextIndex
                                    return
                                }
                                if (updateMap(result, nextIndex)) {
                                    countList[nextIndex].add(result)
                                }
                            }
                            Func.DIV -> {
                                if (bb == 0.0 || aa == 0.0) return@aa
                                val result = aa.toInt() / bb.toInt()
                                val result2 = bb.toInt() / aa.toInt()
                                if (minCount > nextIndex && (result.toDouble() == number || result2.toDouble() == number)) {
                                    minCount = nextIndex
                                    return
                                }
                                if (updateMap(result.toDouble(), nextIndex)) {
                                    countList[nextIndex].add(result.toDouble())
                                }
                                if (updateMap(result2.toDouble(), nextIndex)) {
                                    countList[nextIndex].add(result2.toDouble())
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    enum class Func {
        ADD,
        SUB,
        MUL,
        DIV
    }
}