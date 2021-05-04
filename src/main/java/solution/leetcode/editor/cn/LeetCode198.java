package solution.leetcode.editor.cn;

public class LeetCode198 {
    static class MySolution {
        //上述方法使用了数组存储结果。考虑到每间房屋的最高总金额只和该房屋的前两间房屋的最高总金额相关，因此可以使用滚动数组，
        // 在每个时刻只需要存储前两间房屋的最高总金额。
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            }
            int[] dp = new int[length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < length; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[length - 1];
        }
//  public int rob(int[] nums) {
//        int[] f = new int[nums.length + 2] ;
//        for(int i = 0;i < nums.length; i++) {
//            f[i+2] = Math.max(f[i] + nums[i], f[i+1]);
//        }
//        return f[nums.length+1];
//    }
        public int robOpt(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            }
            int first = nums[0], second = Math.max(nums[0], nums[1]);
            for (int i = 2; i < length; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }

        public int robMoreElegent(int[] nums) {
            int a = 0, b = 0;
            for (int i = 0; i < nums.length; i++) {
                int c = Math.max(b, a + nums[i]);
                a = b;
                b = c;
            }
            return b;
        }

        public static void main(String[] args) {
            MySolution solution = new MySolution();
            int[] arr = {1, 2, 3, 1};
            int[] arr1 = {2, 7, 9, 3, 1};
            System.out.println(solution.rob(arr));
            System.out.println(solution.rob(arr1));
        }
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            //边界值判断
            if (nums == null || nums.length == 0) return 0;
            //如果长度为1,直接偷了
            if (nums.length == 1) {
                return nums[0];
            }
            //长度大于1了
            int[] dp = new int[nums.length];
            //dp[0]设值
            dp[0] = nums[0];
            //dp[1]设值,0和1偷最大
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                //很简单了,
                dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
            }
            return dp[nums.length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
