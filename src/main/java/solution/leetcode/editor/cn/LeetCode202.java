package solution.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class LeetCode202 {
    class SolutionPoint {
        public int getNext(int n) {
            int totalSum = 0;
            while (n > 0) {
                int d = n % 10;
                n = n / 10;
                totalSum += d * d;
            }
            return totalSum;
        }

        public boolean isHappy(int n) {
            int slowRunner = n;
            int fastRunner = getNext(n);
            while (fastRunner != 1 && slowRunner != fastRunner) {
                slowRunner = getNext(slowRunner);
                fastRunner = getNext(getNext(fastRunner));
            }
            return fastRunner == 1;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int getNext(int n) {
            //基本操作求出平方和
            int totalSum = 0;
            while (n > 0) {
                //模10
                int d = n % 10;
                //除10
                n = n / 10;
                totalSum += d * d;
            }
            return totalSum;
        }

        //
        public boolean isHappy(int n) {
            //检测循环
            Set<Integer> seen = new HashSet<>();
            while (n != 1 && !seen.contains(n)) {
                seen.add(n);
                n = getNext(n);
            }
            return n == 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
