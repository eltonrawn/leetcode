package solutions.kotlin

class `295_findMedian` {
    val firSet = sortedSetOf(compareBy<Pair<Int, Int>> {it.first}.thenBy{it.second})
    val secSet = sortedSetOf(compareBy<Pair<Int, Int>> {it.first}.thenBy{it.second})

    fun addNum(num: Int) {
        // add to the firstSet first
        firSet.add(Pair(num, firSet.size + secSet.size))
        // rebalance to swap while firSet.last > secSet.first
        while(firSet.isNotEmpty() && secSet.isNotEmpty() && firSet.last().first > secSet.first().first) {
            val a = firSet.last()
            val b = secSet.first()
            firSet.remove(firSet.last())
            secSet.remove(secSet.first())
            firSet.add(b)
            secSet.add(a)
        }
        while((firSet.size - secSet.size) > 1) {
            secSet.add(firSet.last())
            firSet.remove(firSet.last())
        }
        println("foo")
        println(firSet)
        println(secSet)
    }

    fun findMedian(): Double {
        if(firSet.size == secSet.size) {
            return (firSet.last().first + secSet.first().first) / 2.0
        } else { // otherwise firSet should contain the extra element
            return firSet.last().first.toDouble()
        }
    }
}
/**
 * category:
 * tag: revise for compact solution, revise for sorting in sorted set, sortedset
 *
 * */