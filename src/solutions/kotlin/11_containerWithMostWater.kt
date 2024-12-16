package solutions.kotlin

import kotlin.math.abs

class `11_containerWithMostWater` {
    var monQueue = MutableList(0) {Pair(0, 0)}
    var ans = 0

    fun processPos(i: Int, height: IntArray) {
        val h = height[i]
        var lo = 0
        var hi = monQueue.size - 1
        while(lo < hi) {
            val mid = lo + (hi - lo) / 2
            if(monQueue[mid].first >= h) {
                hi = mid
            }
            else {
                lo = mid + 1
            }
        }

        if(monQueue.isNotEmpty() && monQueue[lo].first >= h) {
            ans = maxOf(ans, abs(i - monQueue[lo].second) * h)
        }

        if(monQueue.isEmpty() || h > monQueue.last().first) {
            monQueue.add(Pair(h, i))
        }
    }

    fun maxArea(height: IntArray): Int {
        /*
            this uses binary search approach on monotonic queue
        */

        // scan from left to right
        for(i in height.indices) {
            processPos(i, height)
        }

        monQueue = MutableList(0) {Pair(0, 0)}

        // scan from right to left
        for(i in height.size - 1 downTo 0) {
            processPos(i, height)
        }
        return ans
    }

    fun maxArea2(height: IntArray): Int {
        /*
            we can do two pointer
            one start from left and one start from right
            if height of any pointer less than other than it is ok to move that pointer with less height because that
            pointer already maximized it's potential.
            let's say if we increase the other pointer instead of this, then it doesn't matter if the new pointer's
            height is more or less. In both cases it will be less than current ans.

            What happens if both pointers are same height. then we can just increase both the pointers because they
            both maximized their potential. new answer can only come if there's two pointers between them having height
             more than current.

        */
        var lo: Int = 0
        var hi: Int = height.size - 1
        var ans = 0
        while(lo < hi) {
            ans = maxOf(ans, (hi - lo) * minOf(height[lo], height[hi]))
            if(height[lo] == height[hi]) {
                lo++
                hi--
            } else if(height[lo] < height[hi]) {
                lo++
            } else {
                hi--
            }
        }
        return ans
    }
}
/**
 * category:
 * tag: monotonic queue, medium, binary search, revise, revise editorial, optimization, proof, greedy, two pointer
 *
 * */