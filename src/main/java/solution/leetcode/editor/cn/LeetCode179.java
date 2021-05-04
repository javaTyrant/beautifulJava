package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode179 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static String largestNumber(int[] nums) {
            //构建一个string数组
            String[] h = new String[nums.length];
            //每个数组放一个数
            for (int i = 0; i < nums.length; i++)
                h[i] = String.valueOf(nums[i]);
            //排序
            Arrays.sort(h, (a, b) -> {
                String l1 = a + b;
                String l2 = b + a;
                return l2.compareTo(l1);
            });
            //
            if (h[0].charAt(0) == '0') return "0";
            StringBuilder sb = new StringBuilder();
            for (String ky : h) sb.append(ky);
            return sb.toString();
        }

        public static void main(String[] args) {
            int[] arr = {3, 30, 4};
            System.out.println(largestNumber(arr));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
