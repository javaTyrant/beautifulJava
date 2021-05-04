package solution.leetcode.editor.cn;

public class LeetCode875 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //原来是二分法
        public int minEatingSpeed(int[] piles, int H) {
            int lo = 1;
            int hi = 1_000_000_000;
            while (lo < hi) {
                int mi = (lo + hi) / 2;
                if (!possible(piles, H, mi))
                    lo = mi + 1;
                else
                    hi = mi;
            }

            return lo;
        }

        // Can Koko eat all bananas in H hours with eating speed K?
        public boolean possible(int[] piles, int H, int K) {
            int time = 0;
            //吃掉香蕉的时间小于警卫回来的时间
            for (int p : piles)
                time += (p - 1) / K + 1;
            return time <= H;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
