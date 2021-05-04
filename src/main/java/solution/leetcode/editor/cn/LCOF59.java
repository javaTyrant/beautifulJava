package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;

public class LCOF59 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static int[] maxSlidingWindow(int[] nums, int k) {
            int numLen = nums.length;
            if (numLen == 0) return new int[0];
            int[] ans = new int[numLen - k + 1]; // 保存结果
            int left = 0; // 左指针
            int right = k - 1; // 右指针
            int max = -1; //保存最大值的指针

            while (right < numLen) {
                if (max < left) { // 更新最大值
                    max = left;
                    for (int i = left; i <= right; i++) {
                        max = nums[max] < nums[i] ? i : max;
                    }
                } else {
                    //更新最大值
                    max = nums[max] < nums[right] ? right : max;
                }
                ans[left] = nums[max];
                left++;
                right++;
            }
            return ans;
        }

        public int[] maxSlidingWindowPass(int[] nums, int k) {
            if (nums == null || nums.length < 2) return nums;
            // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数按从大到小排序
            LinkedList<Integer> list = new LinkedList();
            // 结果数组
            int[] result = new int[nums.length - k + 1];
            for (int i = 0; i < nums.length; i++) {
                // 保证从大到小 如果前面数小 弹出
                while (!list.isEmpty() && nums[list.peekLast()] <= nums[i]) {
                    list.pollLast();
                }
                // 添加当前值对应的数组下标
                list.addLast(i);
                // 初始化窗口 等到窗口长度为k时 下次移动在删除过期数值
                if (list.peek() <= i - k) {
                    list.poll();
                }
                // 窗口长度为k时 再保存当前窗口中最大值
                if (i - k + 1 >= 0) {
                    result[i - k + 1] = nums[list.peek()];
                }
            }
            return result;
        }

        public static void main(String[] args) {
            int[] arr = {1, 3, 2, 1, 4, 6};
            System.out.println(Arrays.toString(maxSlidingWindow(arr, 3)));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
