package solutions

import java.util.*

class `3362_zeroArrayTransformation` {
    fun maxRemoval(nums: IntArray, queries: Array<IntArray>): Int {
        queries.sortWith(compareBy<IntArray> {it[0]}.thenByDescending {it[1]})

        var pos = 0
        var queryPos = 0
        val pq = PriorityQueue< List<Int> > ( compareByDescending<List<Int>> {it[1]}.thenBy {it[0]} )
        var cover = 0
        // var inCover = MutableList(queries.size) {0}
        val outCover = MutableList(nums.size) {0}

        while(pos < nums.size) {
            while(queryPos < queries.size && queries[queryPos][0] <= pos) {
                pq.add(queries[queryPos].toList())
                queryPos++
            }
            // cover += inCover[pos]
            while(cover < nums[pos] && pq.isNotEmpty()) {
                val top = pq.peek()
                if(top[1] >= pos) {
                    cover++
                    outCover[top[1]]++
                }

                pq.poll()
            }
            if(cover < nums[pos]) {
                return -1
            }
            println("$pos : $cover")
            cover -= outCover[pos]
            pos++
        }

        return pq.size
    }
}
/**
 * category:
 * tag: priority_queue, sort, query, medium, revise
 *
 * */