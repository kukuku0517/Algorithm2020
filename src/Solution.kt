import java.lang.Exception
import java.util.*

/**
 * 18434600
 *
 */
class Solution {

    var number: Int = 0
    var N: Int = 0
    var minCount = Int.MAX_VALUE
    val resultMap = mutableMapOf<Int, Int>()
    var count = 0
    fun solution(N: Int, number: Int): Int {
        if (N == number) return 1
        this.N = N
        this.number = number

        resultMap.set(N, 1)

        Func.values().forEach {
            when (it) {
                Func.NUM -> queue.add(Result(1, mutableListOf(it), result = N, hasBracket = 0, funcSize = 1))
                Func.OPEN -> queue.add(Result(0, mutableListOf(it), result = 0, hasBracket = 1, funcSize = 1))
            }
        }
        while (queue.isNotEmpty()) {
            val result = queue.poll()
            count++
            go(result)
        }

        println(count)
        return if (minCount == Int.MAX_VALUE) -1 else minCount
    }

    val MAX_COUNT = 8

    fun go(result: Result) {
        if (minCount < result.count + 1 || result.count >= MAX_COUNT) return
        Func.values().forEach {
            when (it) {
                Func.NUM -> {
                    if (result.func.last() == Func.CLOSE) return@forEach

                    val newFunc = result.func.addCopy(it)
                    if (result.hasBracket > 0) {
                        queue += result.copy(
                            count = result.count + 1,
                            func = newFunc,
                            funcSize = newFunc.size
                        )
                        return@forEach
                    } else {
                        val calResult = calculateResult(newFunc, result.count + 1)
                        calResult ?: return@forEach
//                          println("  ${result.count + 1}")
                        if (calResult == this.number) {
                            minCount = result.count + 1
                            return
                        }
                        queue += result.copy(
                            count = result.count + 1,
                            func = newFunc,
                            result = calResult,
                            funcSize = newFunc.size
                        )
                        return@forEach
                    }


                }
                Func.ADD -> {
                    if (minCount < result.count || result.count >= MAX_COUNT) return@forEach
                    if (result.func.last().isCalOrOpen()) return@forEach
                    val newFunc = result.func.addCopy(it)

                    queue += result.copy(

                        func = newFunc,
                        funcSize = newFunc.size
                    )
                }
                Func.SUB -> {
                    if (minCount < result.count || result.count >= MAX_COUNT) return@forEach
                    if (result.func.last().isCalOrOpen()) return@forEach
                    val newFunc = result.func.addCopy(it)

                    queue += result.copy(

                        func = newFunc,
                        funcSize = newFunc.size
                    )
                }
                Func.MUL -> {
                    if (minCount < result.count || result.count >= MAX_COUNT) return@forEach
                    if (result.func.last().isCalOrOpen()) return@forEach
                    val newFunc = result.func.addCopy(it)

                    queue += result.copy(

                        func = newFunc,
                        funcSize = newFunc.size
                    )
                }
                Func.DIV -> {
                    if (minCount < result.count || result.count >= MAX_COUNT) return@forEach
                    if (result.func.last().isCalOrOpen()) return@forEach
                    val newFunc = result.func.addCopy(it)

                    queue += result.copy(

                        func = newFunc,
                        funcSize = newFunc.size
                    )
                }
                Func.OPEN -> {
                    if (!result.func.last().isCal() || result.func.last() == Func.OPEN) return@forEach
                    val newFunc = result.func.addCopy(it)
                    queue += result.copy(
                        func = newFunc,
                        hasBracket = result.hasBracket + 1,
                        funcSize = newFunc.size
                    )

                }
                Func.CLOSE -> {
                    if (result.hasBracket > 0) {
                        if (result.func.last() != Func.NUM) return@forEach

                        val newFunc = result.func.addCopy(it)
                        val calResult = calculateResult(newFunc, result.count + 1)
//                        println("  ${result.count + 1}")
                        calResult ?: return@forEach
                        if (calResult == this.number) {
                            minCount = result.count + 1
                            return
                        }
                        queue += result.copy(
                            func = newFunc,
                            result = calResult,
                            hasBracket = result.hasBracket - 1,
                            funcSize = newFunc.size
                        )
                    }
                }
            }
        }

    }

    fun MutableList<Func>.addCopy(func: Func): MutableList<Func> {
        val new = mutableListOf<Func>()
        new.addAll(this)
        new.add(func)
        return new;
    }

    fun calculateResult(funcs: List<Func>, count: Int): Int? {
        val funcStack = Stack<Func>()
        val latterList = mutableListOf<String>()
        var tempNum = 0.0

        funcs.forEach {
            if (it == Func.NUM) {
                if (tempNum == 0.0) {
                    tempNum = N.toDouble()
                } else {
                    tempNum = tempNum * 10 + N
                }
            } else {
                if (tempNum != 0.0) {
                    latterList.add(tempNum.toString())
                    tempNum = 0.0
                }
                if (it == Func.CLOSE) {
                    while (funcStack.isNotEmpty() && funcStack.peek() != Func.OPEN) {
                        latterList.add(funcStack.pop().text)
                    }
                } else if (it == Func.OPEN) {
                    funcStack.add(it)
                } else {
                    if (funcStack.isNotEmpty()
                        && (funcStack.peek() == Func.ADD || funcStack.peek() == Func.SUB)
                        && (it == Func.MUL || it == Func.DIV)
                    ) {
                        funcStack.add(it)
                    } else {
                        if (funcStack.isNotEmpty()) {
                            latterList.add(funcStack.pop().text)
                        }
                        funcStack.add(it)
                    }
                }
            }
        }

        if (tempNum != 0.0) {
            latterList.add(tempNum.toString())
        }
        while (funcStack.isNotEmpty()) {
            latterList.add(funcStack.pop().text)
        }

        if (latterList.isEmpty()) return 0

//        var a = -1.0
//        var b = -1.0

        val calStack = Stack<Int>()

        latterList.filterNot { it == "(" || it == ")" }.forEach {
            when (it) {
                Func.ADD.text -> {
                    val b = calStack.pop()
                    val a = calStack.pop()
                    calStack.push(a + b)
                }
                Func.SUB.text -> {
                    val b = calStack.pop()
                    val a = calStack.pop()
                    calStack.push(a - b)
                }
                Func.MUL.text -> {
                    val b = calStack.pop()
                    val a = calStack.pop()
                    calStack.push(a * b)
                }
                Func.DIV.text -> {
                    val b = calStack.pop()
                    val a = calStack.pop()
                    if (b != 0) {
                        calStack.push(a / b)
                    } else {
                        return null
                    }
                }
                else -> {
                    calStack.push(it.toDouble().toInt())
                }
            }
        }
        val a = try {
            calStack.pop()
        } catch (e: Exception) {
            return null
        }

        if (resultMap.containsKey(a)) {
            if (resultMap[a]!! <= count) {
//                println("${a} duplicate ${resultMap[a]} <= ${count}")
                return null
            } else {
                resultMap.set(a, count)
            }
        } else {
            resultMap.set(a, count)
        }

//        println("${funcs.map { it.text }
//            .reduce { acc, s -> "$acc $s" }} [ to ] " + latterList.filter { it != "(" || it != ")" }
//            .reduce { acc, s -> "$acc $s" } + "= $a")


        return a.toInt()

    }

    val queue = PriorityQueue<Result>()

    data class Result(
        val count: Int,
        val func: MutableList<Func>,
        val result: Int,
        val hasBracket: Int,
        val funcSize: Int
    ) : Comparable<Result> {
        override fun compareTo(other: Result): Int {
//            return this.count + func.count { it == Func.NUM } - (other.count + other.func.count { it == Func.NUM })
            return if (this.count != other.count) {
                this.count - other.count
            } else {
                this.funcSize - other.funcSize
            }
        }

        override fun toString(): String {
            if (func.isEmpty()) {
                return ""
            }
            return func.map { it.text }.reduce { acc, s -> "$acc $s" }
        }
    }

    enum class Func(val text: String) {
        NUM("5"),
        ADD("+"),
        SUB("-"),
        MUL("*"),
        DIV("/"),
        OPEN("("),
        CLOSE(")");

        fun isCal(): Boolean {
            return this == ADD || this == SUB || this == MUL || this == DIV
        }

        fun isCalOrOpen(): Boolean {
            return this == ADD || this == SUB || this == MUL || this == DIV || this == OPEN
        }

    }
}