package solutions.kotlin

class `424_longestRepeating` {
    fun characterReplacement(s: String, k: Int): Int {
        var ans = 0
        for(ch in 'A' .. 'Z') {
            var le = 0
            var ri = 0
            var kRemain = k
            while(le < s.length) {
                // increase ri until it doesn't fulfill
                while((ri < s.length) && (s[ri] == ch || kRemain > 0)) {
                    if(s[ri] != ch) {
                        kRemain--
                    }
                    ri++
                }
                ans = maxOf(ans, ri - le)

                // increase le as we already have best answer including it
                if(s[le] != ch && le < ri) {
                    kRemain++
                }
                le++
                while(le > ri) {
                    ri++
                }
            }
        }
        return ans
    }
}

/**
 * tag: medium, revise, sliding window, two pointer
 *
 * solution here is O (n & 26), revise for O(n) in the editorial
 * */