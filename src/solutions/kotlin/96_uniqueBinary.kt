package solutions.kotlin

class `96_uniqueBinary` {
    val dp = MutableList(20) {MutableList(20) {-1}}
    fun func(le: Int, ri: Int): Int {
        if(le > ri) {
            return 1
        }
        if(dp[le][ri] != -1) {
            return dp[le][ri]
        }
        var ans = 0
        for(i in le .. ri) {
            // assign i in current

            // left (le, i - 1)
            val ale = func(le, i - 1)
            // right (i + 1, ri)
            val ari = func(i + 1, ri)
            ans += ale * ari
        }
        dp[le][ri] = ans
        return ans
    }

    fun numTrees(n: Int): Int {
        return func(1, n)
    }
}
/**
 * category:
 * tag: recursion, tree
 *
 * */