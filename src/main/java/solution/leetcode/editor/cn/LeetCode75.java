package solution.leetcode.editor.cn;

public class LeetCode75 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //75. 颜色分类
        //输入：nums = [2,0,2,1,1,0]
        //输出：[0,0,1,1,2,2]
        public static void sortColors(int[] nums) {
            //三指针
            int low = 0, high = nums.length - 1;
            int i = 0;
            while (i <= high) {
                //等于0,交换low和i,low和i++
                if (nums[i] == 0) {
                    swap(nums, low, i);
                    ++low;
                    ++i;
                    //等于1 i++
                } else if (nums[i] == 1) {
                    ++i;
                    //等于2 交换high和i,high--
                } else if (nums[i] == 2) {
                    swap(nums, high, i);
                    --high;
                }
            }
        }

        private static void swap(int[] nums, int low, int i) {
            int tmp = nums[i];
            nums[i] = nums[low];
            nums[low] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
