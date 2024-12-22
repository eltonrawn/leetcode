package solutions.kotlin

class `39_combinationSum` {
    var target: Int = 0
    var dp = MutableList(31) {MutableList(41) {-1}}
    var candidates = MutableList(0) {-1}
    var ret = MutableList(0) {List(0) {0}}
    var cur = MutableList(0) {0}

    fun func(pos: Int, sum: Int): Int {
        if(sum == target) {
            dp[pos][sum] = 1
            return 1
        }
        if(sum > target) {
            return 0
        }
        if(pos == candidates.size) {
            return 0
        }
        var ans = 0
        // take it
        ans = maxOf(ans, func(pos, sum + candidates[pos]))
        // move forward
        ans = maxOf(ans, func(pos + 1, sum))
        dp[pos][sum] = ans
        return ans
    }

    fun checkDp(pos: Int, sum: Int): Int {
        if(!(pos in 0 ..< dp.size && sum in 0 ..< dp[0].size)) {
            return 0
        }
        return dp[pos][sum]
    }

    fun bt(pos: Int, sum: Int) {
        if(sum == target) {
            ret.add(cur.toList())
            return
        }
        if(sum > target) {
            return
        }
        if(pos == candidates.size) {
            return
        }
        // take it
        if(checkDp(pos, sum + candidates[pos]) == 1) {
            cur.add(candidates[pos])
            bt(pos, sum + candidates[pos])
            cur.removeLast()
        }

        // move forward
        if(checkDp(pos + 1, sum) == 1) {
            bt(pos + 1, sum)
        }
    }
    fun combinationSum(_candidates: IntArray, _target: Int): List<List<Int>> {
        target = _target
        candidates = _candidates.toMutableList()
        func(0, 0)
        bt(0, 0)
        return ret.toList()
    }
}
/**
 * category:
 * tag: backtrack, dp
 *
 * */