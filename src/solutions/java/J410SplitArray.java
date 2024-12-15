package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;

public class J410SplitArray {
    Boolean isValid(int sum, int[] nums, int k) {
        var ss = 0;
        var kUsed = 0;
        for(var num: nums) {
            if(ss + num > sum) {
                kUsed++;
                ss = num;
            } else {
                ss += num;
            }
        }
        if(kUsed <= k) {
            return true;
        }
        return false;
    }
    public int splitArray(int[] nums, int k) {
        var lo = Arrays.stream(nums).max().orElse(0);
        var hi = Arrays.stream(nums).sum();
        while(lo < hi) {
            var mid = lo + (hi - lo) / 2;
            if(isValid(mid, nums, k)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;

    }
    public void main() {
        var yo = new J410SplitArray();
        yo.splitArray(new int[]{10, 2}, 2);
    }
}

