import java.util.*

class Solution4 {

    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        var answer = 0
        val stack = Stack<Int>()

        val boardStack = Array<Stack<Int>>(board[0].size) { Stack() }
        board.reversed().forEach { ints ->
            ints.forEachIndexed { index, i ->
                if (i!=0){
                    boardStack[index].push(i)
                }
            }
        }
        moves.forEach {
            val idx = it - 1
            if (boardStack[idx].isEmpty()) return@forEach
            val value = boardStack[idx].pop()
            if (stack.isNotEmpty()) {
                if (stack.peek() == value) {
                    stack.pop()
                    answer += 2
                } else {
                    stack.push(value)
                }
            } else {
                stack.push(value)
            }
        }


        return answer

    }

}