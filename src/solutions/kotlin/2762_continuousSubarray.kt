package solutions.kotlin

import kotlin.math.abs
import java.util.Deque

class `2762_continuousSubarray` {
    fun continuousSubarrays(nums: IntArray): Long {
        var le = 0
        var ri = 0
        var diff = 0
        val st = sortedSetOf<Pair<Int, Int>>(compareBy<Pair<Int, Int>> {it.first}.thenBy {it.second})
        var ans = 0L
        while(le < nums.size) {
            // increase the right pointer till it's ok
            while(ri < nums.size) {
                st.add(Pair(nums[ri], ri))
                if(abs(st.last().first - st.first().first) > 2) {
                    st.remove(Pair(nums[ri], ri))
                    break
                }
                ri++
            }

            // increase left pointer once and get ans
            ans += st.size
            st.remove(Pair(nums[le], le))
            le++
        }
        return ans
    }

    fun continuousSubarraysDeque(nums: IntArray): Long {
        var le = 0
        var ri = 0
        var diff = 0
        val mxQueue = ArrayDeque<Pair<Int, Int>>()
        val mnQueue = ArrayDeque<Pair<Int, Int>>()
        var ans = 0L
        while(le < nums.size) {
            // increase the right pointer till it's ok
            while(ri < nums.size) {
                if(abs(maxOf(nums[ri], if(mxQueue.isNotEmpty()) (mxQueue.first().first) else 0) -
                            minOf(nums[ri], if(mnQueue.isNotEmpty()) (mnQueue.first().first) else 1000000001)) > 2) {
                    break
                }

                while(mxQueue.isNotEmpty() && mxQueue.last().first <= nums[ri]) {
                    mxQueue.removeLast()
                }
                mxQueue.addLast(Pair(nums[ri], ri))

                while(mnQueue.isNotEmpty() && mnQueue.last().first >= nums[ri]) {
                    mnQueue.removeLast()
                }
                mnQueue.addLast(Pair(nums[ri], ri))

                ri++
            }

            // increase left pointer once and get ans
            ans += (ri - le)
            if(mxQueue.first().second == le) {
                mxQueue.removeFirst()
            }
            if(mnQueue.first().second == le) {
                mnQueue.removeFirst()
            }
            le++
        }
        return ans
    }
}

/**
 * tag: medium, revise, two pointer, revise for O(n), deque, monotonic queue
 *
 * */