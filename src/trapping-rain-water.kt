import java.util.*
import kotlin.math.min

class Solution {
    val stack = Stack<Pair<Int, Int>>()

    fun trap(height: IntArray): Int {
        var result = 0

        height.forEachIndexed { index, i ->
            var min = 0
            if (stack.isNotEmpty()) {
                var lastFloor = 0
                while (stack.isNotEmpty() && i >= min && index - stack.peek().second > 1) {
                    val last = stack.pop()
                    println("pop ${last.first} ${last.second} / $i $index")
                    result += (index - last.second - 1) * (min(last.first, i) - lastFloor)
                    lastFloor = min(last.first, i)

                }
                if (i > 0 && (stack.isEmpty() || stack.peek().second >= i)) {
                    println("push1 $i $index")
                    stack.push(i to index)
                }
            } else {
                if (i > 0) {
                    println("push2 $i $index")
                    stack.push(i to index)
                }
            }


        }
        return result
    }
}