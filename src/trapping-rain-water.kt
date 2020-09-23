import java.util.*
import kotlin.math.min

class Solution {

    fun trap(height: IntArray): Int {
        var result = 0

        if (height.isEmpty()) return 0
        val size = height.size
        val leftMax = Array(size) { 0 }
        val rightMax = Array(size) { 0 }

        leftMax[0] = height[0]
        rightMax[size - 1] = height[size - 1]

        (1 until size).forEach {
            leftMax[it] = Math.max(leftMax[it - 1], height[it])
        }
        (1 until size).map { size - it - 1 }.forEach {
            rightMax[it] = Math.max(rightMax[it + 1], height[it])
        }
        (0 until size).forEach {
            result += Math.min(leftMax[it], rightMax[it]) - height[it]
        }
        return result
    }
}