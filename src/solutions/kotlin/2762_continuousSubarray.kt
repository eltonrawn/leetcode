package solutions.kotlin

import kotlin.math.abs

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
}

/**
 * tag: medium, revise, two pointer
 *
 * */