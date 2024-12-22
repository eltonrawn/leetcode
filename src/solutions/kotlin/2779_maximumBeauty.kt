package solutions.kotlin

class `2779_maximumBeauty` {
    fun maximumBeauty(nums: IntArray, k: Int): Int {
        val freqPlus = MutableList(300001) {0}
        val freqMinus = MutableList(300001) {0}
        for(num in nums) {
            freqPlus[num - k + 100000]++
            freqMinus[num + k + 100000]++
        }
        var ans = 0
        var freq = 0
        for(i in 0 .. 300000) {
            freq += freqPlus[i]
            ans = maxOf(ans, freq)
            freq -= freqMinus[i]
        }
        return ans
    }
}

/**
 * tag: medium, revise, range query, range sum query,
 *
 * revise for other approach
 *
 * */