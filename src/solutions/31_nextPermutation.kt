package solutions

class `31_nextPermutation` {
    var vis = MutableList(0) {0}
    var n = 0
    var nums = intArrayOf()

    fun nextPermutation(_nums: IntArray): Unit {
        nums = _nums
        n = nums.size
        vis = MutableList(n) {0}

        var pos = -1
        for(i in nums.size - 2 downTo 0) {
            if(nums[i] < nums[i + 1]) {
                pos = i
                break
            }
        }
        val sortedList = MutableList(0) {0}
        for(i in pos + 1 .. nums.size - 1) {
            sortedList.add(nums[i])
        }
        sortedList.sort()
        var j = 0
        for(i in pos + 1 .. nums.size - 1) {
            nums[i] = sortedList[j]
            j++
        }
        if(pos != -1) {
            for(i in pos + 1 .. nums.size - 1) {
                if(nums[i] > nums[pos]) {
                    val tmp = nums[i]
                    nums[i] = nums[pos]
                    nums[pos] = tmp
                    break
                }
            }
        }
    }

    fun nextPermutationInPlace(_nums: IntArray): Unit {
        nums = _nums
        n = nums.size
        vis = MutableList(n) {0}

        var pos = -1
        for(i in nums.size - 2 downTo 0) {
            if(nums[i] < nums[i + 1]) {
                pos = i
                break
            }
        }

        nums.sort(pos + 1, nums.size)

        if(pos != -1) {
            for(i in pos + 1 .. nums.size - 1) {
                if(nums[i] > nums[pos]) {
                    val tmp = nums[i]
                    nums[i] = nums[pos]
                    nums[pos] = tmp
                    break
                }
            }
        }
    }
}
/*
* 1 6 4 2 3 8 7 5
* 1 6 4 2 5 3 7 8
* */

/**
 * tag: hard, revise, simulation, in place, sort
 *
 * */