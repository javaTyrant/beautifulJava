package solution.leetcode.editor.cn;

public class LeetCode670 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //最大交换
        public int maximumSwap(int num) {
            char[] A = Integer.toString(num).toCharArray();
            int[] last = new int[10];
            for (int i = 0; i < A.length; i++) {
                last[A[i] - '0'] = i;
            }

            for (int i = 0; i < A.length; i++) {
                for (int d = 9; d > A[i] - '0'; d--) {
                    if (last[d] > i) {
                        char tmp = A[i];
                        A[i] = A[last[d]];
                        A[last[d]] = tmp;
                        return Integer.valueOf(new String(A));
                    }
                }
            }
            return num;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
