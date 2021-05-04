package solution.leetcode.editor.cn;

public class LeetCode153 {
    static class Solution1 {
        public static int findMin(int[] nums) {
            int i = 0;
            int j = nums.length - 1;
            while (i < j) {
                int mid = (j - i) / 2 + i;
                //最小值在右边
                if (nums[mid] > nums[j]) {
                    i = mid + 1;
                } else if (nums[mid] < nums[j]) {
                    j = mid;
                }
            }
            return nums[i];
        }

        public static void main(String[] args) {
            int[] arr = {3, 4, 5, 1, 2};
            int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
            int[] arr2 = {3, 1, 2};
            System.out.println(findMin(arr2));
            System.out.println(findMin(arr));
            System.out.println(findMin(arr1));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //寻找旋转排序数组中的最小值
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                //左边有序
                if (nums[mid] < nums[right]) {
                    right = mid;
                    //易错点之和right比较
                } else if (nums[mid] > nums[right]) {
                    left = mid + 1;
                } else {
                    return nums[mid];
                }
            }
            return nums[left];
        }

        public static boolean CheckPermutation(String s1, String s2) {
            if (s1.length() != s2.length()) return false;
            int[] dict = new int[26];
            for (char s : s1.toCharArray()) {
                dict[s - 'a']++;
            }
            for (char s : s2.toCharArray()) {
                dict[s - 'a']--;
                if (dict[s - 'a'] < 0) return false;
            }
            return true;
        }

        public static void main(String[] args) {
            System.out.println(CheckPermutation("abc", "abd"));
            Solution solution = new Solution();
            int[] min = {4, 1};
            System.out.println(solution.findMin(min));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
