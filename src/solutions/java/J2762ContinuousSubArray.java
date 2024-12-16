package solutions.java;

import java.util.TreeSet;
import java.util.Comparator;

import static java.lang.Math.abs;

public class J2762ContinuousSubArray {

    static class Pair<F, S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    public long continuousSubarrays(int[] nums) {
        var le = 0;
        var ri = 0;
        var st = new TreeSet<Pair<Integer, Integer>>(
                Comparator.comparing((Pair<Integer, Integer> p) -> p.first).thenComparing(p -> p.second)
        );
        var ans = 0L;
        while(le < nums.length) {
            // increase the right pointer till it's ok
            while(ri < nums.length) {
                st.add(new Pair<>(nums[ri], ri));
                if(abs(st.last().first - st.first().first) > 2) {
                    st.remove(new Pair<>(nums[ri], ri));
                    break;
                }
                ri++;
            }

            // increase left pointer once and get ans
            ans += st.size();
            st.remove(new Pair<>(nums[le], le));
            le++;
        }
        return ans;
    }
}
