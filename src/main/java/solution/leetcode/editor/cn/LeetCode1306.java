package solution.leetcode.editor.cn;

public class LeetCode1306 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public boolean canReach(int[] arr, int start) {
            if (start >= arr.length || start < 0) return false;
            if (arr[start] < 0) return false;
            if (arr[start] == 0) return true;
            arr[start] *= -1;
            return canReach(arr, start + arr[start])
                    || canReach(arr, start - arr[start]);
        }

        public static void main(String[] args) {
            int[] arr = {4, 2, 3, 0, 3, 1, 2};
            Solution solution = new Solution();
            System.out.println(solution.canReach(arr, 5));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
