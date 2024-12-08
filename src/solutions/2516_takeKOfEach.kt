package solutions

class `2516_takeKOfEach` {
    val cnt = MutableList(3) {0}
    fun isOk(k: Int): Boolean {
        if(cnt[0] >= k && cnt[1] >= k && cnt[2] >= k) return true
        return false
    }

    fun takeCharacters(s: String, k: Int): Int {
        if(k == 0) return 0

        var ri = -1
        for(i in s.length - 1 downTo 0) {
            val ch = s[i]
            cnt[ch - 'a']++
            if(isOk(k)) {
                ri = i
                break
            }
        }
        if(ri == -1) return -1
        var ans = (s.length - ri)
        for(i in s.indices) {
            cnt[s[i] - 'a']++
            while(ri < s.length) {
                cnt[s[ri] - 'a']--
                if(!isOk(k)) {
                    cnt[s[ri] - 'a']++
                    break
                }
                ri++
            }
            ans = minOf(ans, i + 1 + (s.length - ri))
        }
        return ans
    }
}
/**
 * tag: sliding window
 *
 * */