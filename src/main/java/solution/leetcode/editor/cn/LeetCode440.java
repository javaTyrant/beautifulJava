package solution.leetcode.editor.cn;

public class LeetCode440 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            //System.out.println(calSteps(1989, 1, 2));
            System.out.println(findKthNumber(1989, 1101));
        }

        public static int findKthNumber(int n, int k) {
            int curr = 1;
            k = k - 1;
            while (k > 0) {
                int steps = calSteps(n, curr, curr + 1);
                //System.out.println("curr:" + curr);
                //System.out.println("steps:" + steps);
                if (steps <= k) {
                    curr += 1;
                    k -= steps;
                } else {
                    curr *= 10;
                    k -= 1;
                }
            }
            return curr;
        }

        //计算n个数字内以1开头的有多少数字
        //use long in case of overflow
        public static int calSteps(int n, long n1, long n2) {
            int steps = 0;
            while (n1 <= n) {
                steps += Math.min(n + 1, n2) - n1;
                n1 *= 10;
                n2 *= 10;
            }
            return steps;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
