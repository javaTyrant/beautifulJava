package solution.leetcode.editor.cn;

import java.util.*;

public class LeetCode496 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //496. 下一个更大元素 I
        // nums1 = [4,1,2], nums2 = [1,3,4,2].
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int len2 = nums2.length;

            if (len1 == 0) {
                return new int[0];
            }

            int max = 0;
            for (int temp : nums2) {
                max = Math.max(max, temp);
            }

            int[] hash = new int[max + 1];
            for (int i = 0; i < len2; i++) {
                hash[nums2[i]] = i;
            }

            s1:
            for (int i = 0; i < len1; i++) {
                int temp = nums1[i];
                int start = hash[temp];
                nums1[i] = -1;
                s2:
                for (int j = start + 1; j < len2; j++) {
                    if (nums2[j] > temp) {
                        nums1[i] = nums2[j];
                        break s2;
                    }
                }
            }
            return nums1;
        }

        public int[] nextGreaterElementEasy(int[] nums1, int[] nums2) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums2.length; i++) {
                map.put(nums2[i], i);
            }

            int[] ans = new int[nums1.length];
            int len = 0;

            for (int num : nums1) {
                int i = map.get(num) + 1;
                while (i < nums2.length) {
                    if (nums2[i] > num) {
                        ans[len++] = nums2[i];
                        break;
                    }
                    i++;
                }
                if (i >= nums2.length) {
                    ans[len++] = -1;
                }
            }
            return ans;
        }

        //单调栈
        public int[] nextGreaterElementStand(int[] findNums, int[] nums) {
            Deque<Integer> stack = new ArrayDeque<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            int[] res = new int[findNums.length];
            for (int i = 0; i < nums.length; i++) {
                //如果当前的元素大于栈顶的元素,那么说明栈顶的元素找了了最大值,存入map
                //否则把当前的元素放入栈中
                while (!stack.isEmpty() && nums[i] > stack.peek())
                    map.put(stack.pop(), nums[i]);
                stack.push(nums[i]);
            }
            //栈非空的话,说明都是一些没有最大元素的
            while (!stack.isEmpty())
                map.put(stack.pop(), -1);
            for (int i = 0; i < findNums.length; i++) {
                res[i] = map.get(findNums[i]);
            }
            return res;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {4, 1, 2};
            int[] arr2 = {1, 3, 4, 2};
            System.out.println(Arrays.toString(solution.nextGreaterElement(arr, arr2)));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
