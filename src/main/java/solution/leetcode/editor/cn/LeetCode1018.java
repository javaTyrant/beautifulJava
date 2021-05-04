package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;

public class LeetCode1018 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            int[] arr = {0, 1, 1, 1, 1, 1};
            System.out.println(prefixesDivBy5(arr));
        }

        //主要就是把二进制转成10进制
        public static List<Boolean> prefixesDivBy5(int[] A) {
            Boolean[] ans = new Boolean[A.length];
            Arrays.fill(ans, false);
            int x = 0;
            for (int i = 0; i < A.length; i++) {
                x = 2 * x + A[i];
                x = x % 5;
                if (x == 0) ans[i] = true;
            }
            return Arrays.asList(ans);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
