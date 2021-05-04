package solution.leetcode.editor.cn;

public class LeetCode713 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //乘积小于K的子数组,过了79个用例
        //如果全是1,这种重复数据怎么处理呢
        public static int numSubarrayProductLessThanK(int[] nums, int k) {
            int count = 0;
            int res = 1;
            int i = 0;
            while (i < nums.length) {
                int j = i + 1;
                if (nums[i] < k) {
                    count++;
                    res = res * nums[i];
                    while (j < nums.length && (res *= nums[j]) < k) {
                        count++;
                        j++;
                    }
                    i++;
                    res = 1;
                } else {
                    i++;
                    res = 1;
                }
            }
            return count;
        }

        //比我自己的牛逼太多了
        public static int numSubarrayProductLessThanKOk(int[] nums, int k) {
            if (k <= 1) return 0;
            int prod = 1, ans = 0, left = 0;
            for (int right = 0; right < nums.length; right++) {
                prod *= nums[right];
                //如果大于k,那么就从左边除,太牛逼了
                while (prod >= k) {
                    prod /= nums[left++];
                }
                ans += right - left + 1;
            }
            return ans;
        }

        public static void main(String[] args) {
            int[] arr = {10, 5, 2, 6};
            int[] arr1 = {10};
            System.out.println(numSubarrayProductLessThanKOk(arr, 100));
            System.out.println(numSubarrayProductLessThanK(arr, 2));
            System.out.println(numSubarrayProductLessThanK(arr1, 1));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
