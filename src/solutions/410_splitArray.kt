package solutions

class `410_splitArray` {
    fun valid(sum: Int, nums: IntArray, k: Int): Boolean {
        var ss = 0
        var kused = 1
        for(i in nums.indices) {
            if(ss + nums[i] > sum) {
                kused++
                ss = nums[i]
            } else {
                ss += nums[i]
            }
        }
        if(kused <= k) {
            return true
        }
        return false
    }
    fun splitArray(nums: IntArray, k: Int): Int {
        var lo = nums.max()
        var hi = nums.sum()
        while(lo < hi) {
            val mid = lo + ((hi - lo) / 2)
            if(valid(mid, nums, k)) {
                hi = mid
            } else {
                lo = mid + 1
            }
        }
        return lo
    }
}

fun main() {
    val yo = `410_splitArray`()
    yo.splitArray(listOf(10, 2).toIntArray(), 2)
}

/**
 * tag: seems hard but easy, revise, binary search
 *
 * */