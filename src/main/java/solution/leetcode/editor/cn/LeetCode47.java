package solution.leetcode.editor.cn;

import java.util.*;

public class LeetCode47 {
    //给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {

        boolean[] vis;

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> perm = new ArrayList<>();
            vis = new boolean[nums.length];
            Arrays.sort(nums);
            backtrack(nums, ans, 0, perm);
            return ans;
        }

        private void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
            if (idx == nums.length) {
                ans.add(new ArrayList<>(perm));
                return;
            }
            for (int i = 0; i < nums.length; ++i) {
                //如何剪枝
                if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                    continue;
                }
                perm.add(nums[i]);
                vis[i] = true;
                backtrack(nums, ans, idx + 1, perm);
                vis[i] = false;
                perm.remove(idx);
            }
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {1, 1, 1};
            System.out.println(solution.permuteUnique(arr));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}