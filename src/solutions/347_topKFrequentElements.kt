package solutions

class `347_topKFrequentElements` {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val thres = 10000
        val cnt = MutableList(20001) {0}
        val freq = MutableList(100001) { MutableList(0) {0} }
        for(num in nums) {
            cnt[num + thres]++
        }
        for(i in cnt.indices) {
            if(cnt[i] > 0) {
                val value = i - thres
                freq[cnt[i]].add(value)
            }
        }
        val sortedNums = MutableList(0) {0}
        for(i in 100000 downTo 1) {
            for(value in freq[i]) {
                sortedNums.add(value)
            }
        }
        return sortedNums.take(k).toIntArray()
    }
}
/**
 * tag: linear sort, bucket sort
 *
 * */