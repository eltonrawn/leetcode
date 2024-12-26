package solutions.kotlin

class `146_lruCache`(capacity: Int) {
    val cap = capacity
    var tm = 0
    // key, time
    var tmSet = sortedSetOf(compareBy<Pair<Int, Int>> {it.second}.thenBy{it.first})
    var cache = mutableMapOf<Int, Int>()
    var tmIdx = MutableList(10001) {-1}


    fun updateKeyTime(key: Int) {
        if(cache[key] != null) {
            tmSet.remove(Pair(key, tmIdx[key]))
        }
        tm++
        tmIdx[key] = tm
        tmSet.add(Pair(key, tmIdx[key]))
    }

    fun get(key: Int): Int {
        if(cache[key] != null) {
            updateKeyTime(key)
            return cache[key] ?: -1
        } else {
            return -1
        }
    }

    fun put(key: Int, value: Int) {
        updateKeyTime(key)
        cache[key] = value
        while(cache.size > cap) {
            cache.remove(tmSet.first().first)
            tmSet.remove(tmSet.first())
        }
    }
}

class `146_lruCacheLinkedList`(capacity: Int) {
    val cap = capacity

    class Node(var next: Node?, var prev: Node?, var key: Int, var value: Int)

    var head: Node? = null
    var tail: Node? = null

    val map = mutableMapOf<Int, Node>()

    fun removeNode(node: Node?) {
        // prev > b > next
        val prev = node?.prev
        val next = node?.next
        if(prev != null) {
            prev.next = next
        } else {
            head = next
        }
        if(next != null) {
            next.prev = prev
        } else {
            tail = prev
        }

        // reset current node
        node?.prev = null
        node?.next = null
    }

    fun add(node: Node?) {
        if(head == null) {
            head = node
        }

        node?.prev = tail
        tail?.next = node
        tail = node
    }

    fun get(key: Int): Int {
        if(map[key] == null) {
            return -1
        } else {
            val node = map[key]
            removeNode(map[key])
            add(node)
            return node?.value ?: -1
        }
    }

    fun printNode() {
        var cur = head
        while(cur != null) {
            print("(${cur.key}, ${cur.value}),")
            cur = cur.next
        }
        println()
    }

    fun put(key: Int, value: Int) {
        if(map[key] == null) {
            val node = Node(null, null, key, value)
            add(node)
            map[key] = node
        } else {
            val node = map[key]
            node?.value = value
            removeNode(node)
            add(node)
            map[key] = node!!
        }
        // println("kv $key $value")
        // println("before")
        // printNode()
        while(map.size > cap) {
            val cur = head
            map.remove(cur?.key)
            removeNode(cur)

        }
        // println("after")
        // printNode()
    }
}

class `146_lruCacheLinkedListV2`(capacity: Int) {
    val cap = capacity

    class Node(var next: Node?, var prev: Node?, var key: Int, var value: Int)

    var head: Node? = null
    var tail: Node? = null

    val map = mutableMapOf<Int, Node>()

    fun removeNode(node: Node?) {
        if(node == null) return
        // prev > b > next
        val prev = node.prev
        val next = node.next
        if(prev != null) {
            prev.next = next
        } else {
            head = next
        }
        if(next != null) {
            next.prev = prev
        } else {
            tail = prev
        }

        // reset current node
        node.prev = null
        node.next = null
    }

    fun add(node: Node?) {
        if(node == null) return
        if(head == null) {
            head = node
        }

        node.prev = tail
        tail?.next = node
        tail = node
    }

    fun get(key: Int): Int {
        val node = map[key]
        if(node == null) {
            return -1
        } else {
            removeNode(map[key])
            add(node)
            return node.value
        }
    }

    fun printNode() {
        var cur = head
        while(cur != null) {
            print("(${cur.key}, ${cur.value}),")
            cur = cur.next
        }
        println()
    }

    fun put(key: Int, value: Int) {
        val node = map[key]
        if(node == null) {
            val nNode = Node(null, null, key, value)
            add(nNode)
            map[key] = nNode
        } else {
            node.value = value
            removeNode(node)
            add(node)
            map[key] = node
        }
        while(map.size > cap) {
            val cur = head
            map.remove(cur?.key)
            removeNode(cur)

        }
    }
}

/**
 * category:
 * tag: revise for linkedlist solution,
 *
 * */