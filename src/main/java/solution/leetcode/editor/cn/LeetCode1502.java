package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode1502 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canMakeArithmeticProgression(int[] arr) {
            Arrays.sort(arr);
            if (arr.length < 2) return false;
            int c = arr[1] - arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] - arr[i - 1] != c) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
