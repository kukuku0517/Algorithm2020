import java.lang.Exception

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution7 {
    fun addTwoNumbers(l1: ListNode, l2: ListNode): ListNode? {
        return go(l1, l2, 0)
    }

    /**
     * 둘다 있으면 더하기 extra와 함께
     * 하나만 있으면 extra와 함께 넣기
     * extra때문에 넘으면 한번 더넘기기
     *
     * 계산후 1의 자리는 남기고 extra와 뒷 node들을 넘김.
     */
    private fun go(l1: ListNode?, l2: ListNode?, extra: Int): ListNode? {
        when {
            l1 == null && l2 == null -> {
                return if (extra > 0) {
                    ListNode(1, null)
                } else {
                    null
                }
            }
            l1 == null && l2 != null -> {
                return ListNode((l2.`val` + extra) % 10, go(null, l2.next, (l2.`val` + extra) / 10))
            }
            l1 != null && l2 == null -> {
                return ListNode((l1.`val` + extra) % 10, go(l1.next, null, (l1.`val` + extra) / 10))
            }
            else -> {
                return ListNode(
                    (l1!!.`val` + l2!!.`val` + extra) % 10,
                    go(l1.next, l2.next, (l1!!.`val` + l2!!.`val` + extra) / 10)
                )
            }
        }
    }
}

data class ListNode(
    var `val`: Int,
    var next: ListNode? = null
) {
    override fun toString(): String {
        var str = "$`val` ->"
        var nextNode: ListNode? = next
        while (nextNode != null) {
            str += "${nextNode.`val`} ->"
            nextNode = nextNode.next
        }
        return str
    }
}