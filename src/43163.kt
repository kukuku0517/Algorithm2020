import java.util.*

class Solution11 {
    data class WordNode(
        val value: String,
        val nodes: MutableList<WordNode>,
        var dist: Int = Int.MAX_VALUE
    ) : Comparable<WordNode> {
        override fun compareTo(other: WordNode): Int {
            return other.dist - this.dist
        }
    }

    val wordNodes = mutableListOf<WordNode>()
    val wordQueue: PriorityQueue<WordNode> = PriorityQueue()

    fun solution(begin: String, target: String, words: Array<String>): Int {
        val beginNode =WordNode(begin, mutableListOf(), 0)
        var targetNode: WordNode = WordNode(target, mutableListOf(), 0)
        words.forEach {
            wordNodes.add(WordNode(value = it, nodes = mutableListOf()))
        }
        wordNodes.add(beginNode)
        wordQueue.offer(beginNode)
        wordNodes.forEachIndexed { index, a ->
            if (a.value == target) {
                targetNode = a
            }
            (index + 1 until wordNodes.size).forEach {
                val b = wordNodes[it]
                if (a.value.zip(b.value).filter { it.first != it.second }.size == 1) {
                    a.nodes.add(b)
                    b.nodes.add(a)
                }
            }
        }

        while (wordQueue.isNotEmpty()) {
            go(wordQueue.poll())
        }

        return targetNode.dist
    }

    private fun go(node: WordNode) {
        node.nodes.forEach {
//            println("${node.value} > ${it.value}")
            if (node.dist + 1 < it.dist) {
                it.dist = node.dist + 1
                wordQueue.offer(it)
            }
        }
    }
}

