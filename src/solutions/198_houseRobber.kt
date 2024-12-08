package solutions

class `198_houseRobber` {
    fun func(pos: Int, taken: Int, nums: IntArray, dp: MutableList<MutableList<Int>>): Int {
        if(pos == nums.size) {
            return 0
        }
        if(dp[pos][taken] != -1) {
            return dp[pos][taken]
        }
        var ans = 0
        // don't take
        ans = func(pos + 1, 0, nums, dp)
        // take
        if(taken == 0) {
            ans = maxOf(ans, nums[pos] + func(pos + 1, 1, nums, dp))
        }
        dp[pos][taken] = ans
        return ans
    }
    fun rob(nums: IntArray): Int {
        return func(0, 0, nums, MutableList(nums.size) {MutableList(2) {-1}})
    }
}
/**
 * category:
 * tag: easy, dp
 *
 * */