import java.util.*
import kotlin.math.min

class Solution {
    data class Node(
        val num: Int,
        val nodes: MutableList<Node>,
        var twoFare: Int = Int.MAX_VALUE,
        var aFare: Int = Int.MAX_VALUE,
        var bFare: Int = Int.MAX_VALUE
    ) {
        override fun toString(): String {
            return "$num ($twoFare)($aFare)($bFare) ${nodes.map { it.num }}"
        }
    }

    val nodes = mutableMapOf<Int, Node>()
    var mFares = arrayOf<IntArray>()
    var targetA: Int = 0
    var targetB: Int = 0

    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        mFares = Array(n + 1) { IntArray(n + 1) { Int.MAX_VALUE } }
        this.targetA = a
        this.targetB = b
        initNodes(fares)
        val start = nodes[s]!!.apply {
            twoFare = 0
        }

        getTwoFares(start)
        getAFares(nodes[a]!!.apply { aFare = 0 })
        getBFares(nodes[b]!!.apply { bFare = 0 })

        var result = Int.MAX_VALUE
        (1 until n).forEach { i ->
            result = min(result, nodes[i + 1]!!.twoFare + nodes[i + 1]!!.aFare + nodes[i + 1]!!.bFare)
        }
        return result
    }

    private fun getAFares(node: Node) {
        aQueue.offer(node)
        while (aQueue.isNotEmpty()) {
            goAFare(aQueue.poll())
        }
    }

    private fun goAFare(node: Node) {
        node.nodes.forEach {
            val fare = mFares[node.num][it.num]
            if (node.aFare + fare < it.aFare) {
                it.aFare = node.aFare + fare
                aQueue.offer(it)
            }
        }
    }

    private fun getBFares(node: Node) {
        bQueue.offer(node)
        while (bQueue.isNotEmpty()) {
            goBFare(bQueue.poll())
        }
    }

    private fun goBFare(node: Node) {
        node.nodes.forEach {
            val fare = mFares[node.num][it.num]
            if (node.bFare + fare < it.bFare) {
                it.bFare = node.bFare + fare
                bQueue.offer(it)
            }
        }
    }

    val twoQueue: PriorityQueue<Node> = PriorityQueue<Node>(kotlin.Comparator { o1, o2 ->
        o1.twoFare - o2.twoFare
    })
    val aQueue: PriorityQueue<Node> = PriorityQueue<Node>(kotlin.Comparator { o1, o2 ->
        o1.aFare - o2.aFare
    })
    val bQueue: PriorityQueue<Node> = PriorityQueue<Node>(kotlin.Comparator { o1, o2 ->
        o1.bFare - o2.bFare
    })

    private fun getTwoFares(start: Node) {
        twoQueue.offer(start)
        while (twoQueue.isNotEmpty()) {
            goTwoFare(twoQueue.poll())
        }
    }

    private fun goTwoFare(node: Node) {
        node.nodes.forEach {
            val fare = mFares[node.num][it.num]
            if (node.twoFare + fare < it.twoFare) {
                it.twoFare = node.twoFare + fare
                twoQueue.offer(it)
            }
        }
    }

    private fun initNodes(fares: Array<IntArray>) {
        fares.forEachIndexed { _, it ->
//            println("${it[0]}${it[1]}")
            mFares[it[0]][it[1]] = it[2]
            mFares[it[1]][it[0]] = it[2]

            val a = it[0]
            val b = it[1]

            val nodeA = nodes.getOrPut(a) { Node(a, mutableListOf()) }
            val nodeB = nodes.getOrPut(b) { Node(b, mutableListOf()) }

            nodeA.nodes.add(nodeB)
            nodeB.nodes.add(nodeA)
        }
    }
}