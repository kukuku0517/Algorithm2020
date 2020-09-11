import java.util.*

class Solution2 {
    val nodes = mutableMapOf<Int, Node>()
    val queue: Queue<Node> = LinkedList()
    var maxDist = Int.MIN_VALUE

    fun solution(n: Int, edge: Array<IntArray>): Int {
        nodes[1] = Node(num = 1, dist = 0)
        initNodes(edge)
        queue.offer(nodes.get(1))

        while (queue.isNotEmpty()) {
            go(queue.poll())
        }

        val answer = nodes.values.filter { it.dist == maxDist }.size
        return answer
    }

    fun initNodes(edge: Array<IntArray>) {
        edge.forEach {
            val a = it[0]
            val b = it[1]
            val na = nodes.getOrDefault(a, Node(num = a))
            val nb = nodes.getOrDefault(b, Node(num = b))
            na.nodes.add(nb)
            nb.nodes.add(na)
            nodes[a] = na
            nodes[b] = nb
        }
    }

    private fun go(node: Node) {
        node.nodes.forEach {
            if (it.dist == -1 || it.dist > node.dist + 1) {
                it.dist = node.dist + 1
                if (it.dist > maxDist) {
                    maxDist = it.dist
                }
                println("${node.num} -> ${it.num} [${it.dist}]")
                queue.offer(it)
            } else {

            }
        }
    }

    data class Node(
        val num: Int,
        val nodes: MutableList<Node> = mutableListOf(),
        var dist: Int = -1
    )
}