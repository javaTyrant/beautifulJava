package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode611 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            int[] arr = {1, 3, 5, 8, 9, 10, 11, 11, 13, 20};
            System.out.println(triangleNumber(arr));
            System.out.println(triangleNumberEaseUnderstand(arr));
        }

        public static int triangleNumberEaseUnderstand(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            int res = 0;
            for (int i = n - 1; i >= 2; --i) {
                int left = 0, right = i - 1;
                while (left < right) {
                    //如果nums[left] + nums[right] > nums[i]那么后面的肯定都满足
                    //所以累加right - left
                    if (nums[left] + nums[right] > nums[i]) {
                        res += right - left;
                        --right;
                    } else {
                        ++left;
                    }
                }
            }
            return res;
        }

        public static int triangleNumber(int[] nums) {
            //[2,2,3,4]
            Arrays.sort(nums);
            int res = 0;
            for (int i = 0; i <= nums.length - 2; i++) {
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    boolean sa = nums[i] + nums[j] > nums[k];
                    if (sa) {
                        res++;
                        j++;
                    } else {
                        k--;
                    }
                }
            }
            return res;
        }

        //如何思考呢
        public static int triangleNumber1(int[] nums) {
            int count = 0;
            Arrays.sort(nums);
            //两个for
            for (int i = 0; i < nums.length - 2; i++) {
                int k = i + 2;
                //0不能作为三角形的边,干
                for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                    //一个while
                    while (k < nums.length && nums[i] + nums[j] > nums[k])
                        k++;
                    //妙,太骚了这步
                    count += k - j - 1;
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
