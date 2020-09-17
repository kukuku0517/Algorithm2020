//https://leetcode.com/problems/lru-cache/

class LRUCache(val capacity: Int) {
    data class Node(
        val key: Int? = null,
        var value: Int? = null,
        var next: Node? = null,
        var prev: Node? = null
    ) {
        override fun toString(): String {
            return "[${prev?.key}] $key $value [${next?.key}]"
        }
    }

    val nodes = mutableMapOf<Int, Node>()
    var head: Node = Node()
    var tail: Node = Node()
    var count = 0

    init {
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int? {
        val node = nodes.get(key) ?: run {
//            println("get $key null ")
            return -1
        }
//        println("get $key")
        removeNode(node)
//        nodes.put(node.key!!, node)
        addNode(node)
        return node.value
    }


    private fun removeNode(node: Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
        count--
    }

    private fun addNode(node: Node) {
        tail.prev?.next = node
        node.prev = tail.prev
        tail.prev = node
        node.next = tail
        count++

    }

    fun put(key: Int, value: Int) {
//        println("put $key $value")
        val node = nodes[key]?.let {
            nodes[key]!!.value = value
            removeNode(it)
            it
        } ?: run {
            Node(key, value, null, null)
        }
        if (count == capacity) {
            nodes.remove(head.next!!.key)
            removeNode(head.next!!)
        }
        nodes.put(node.key!!, node)
        addNode(node)
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */