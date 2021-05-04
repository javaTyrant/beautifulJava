package solution.leetcode.editor.cn;

import java.util.HashSet;
import java.util.PriorityQueue;

public class LeetCode264 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {

        public static int nthUglyNumber(int n) {
            Ugly ugly = new Ugly();
            return ugly.nums[n - 1];
        }

        public static void main(String[] args) {
            System.out.println(nthUglyNumber(12));
            UglyDp dp = new UglyDp();
            System.out.println(dp.nums[11]);
        }

    }

    //更加的巧妙
    static class UglyDp {
        public int[] nums = new int[1690];

        UglyDp() {
            nums[0] = 1;
            int ugly, i2 = 0, i3 = 0, i5 = 0;

            for (int i = 1; i < 1690; ++i) {
                ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
                nums[i] = ugly;

                if (ugly == nums[i2] * 2) ++i2;
                if (ugly == nums[i3] * 3) ++i3;
                if (ugly == nums[i5] * 5) ++i5;
            }
        }
    }

    static class Ugly {
        public int[] nums = new int[1690];

        Ugly() {
            HashSet<Long> seen = new HashSet();
            PriorityQueue<Long> heap = new PriorityQueue<>();
            //先放两个1
            seen.add(1L);
            heap.add(1L);
            //当前的丑数,新的丑数
            long currUgly, newUgly;
            int[] primes = new int[]{2, 3, 5};
            for (int i = 0; i < 1690; ++i) {
                //思考下
                currUgly = heap.poll();
                //保存
                nums[i] = (int) currUgly;
                //
                for (int j : primes) {
                    //构造新的
                    newUgly = currUgly * j;
                    if (!seen.contains(newUgly)) {
                        seen.add(newUgly);
                        heap.add(newUgly);
                    }
                }
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
