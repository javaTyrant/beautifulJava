package solution.leetcode.editor.cn;

import java.util.PriorityQueue;

public class LeetCode313 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //超级丑数
        public int nthSuperUglyNumber(int n, int[] primes) {
            PriorityQueue<Long> queue = new PriorityQueue<>();
            long res = 1;
            for (int i = 1; i < n; i++) {
                for (int prime : primes) {
                    queue.add(prime * res);
                }
                res = queue.poll();
                while (!queue.isEmpty() && res == queue.peek()) queue.poll();
            }
            return (int) res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
