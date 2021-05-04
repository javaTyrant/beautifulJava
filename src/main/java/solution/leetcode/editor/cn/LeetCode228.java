package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode228 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> ans = new ArrayList<>(nums.length);
            if (nums == null || nums.length == 0) return ans;
            int startIndex = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] + 1 == nums[i]) continue;
                StringBuilder builder = new StringBuilder().append(nums[startIndex]);
                if (startIndex != i - 1) builder.append("->").append(nums[i - 1]);
                ans.add(builder.toString());
                startIndex = i;
            }
            StringBuilder builder = new StringBuilder().append(nums[startIndex]);
            if (startIndex != nums.length - 1) builder.append("->").append(nums[nums.length - 1]);
            ans.add(builder.toString());
            return ans;
        }

        //基本功啊
        public List<String> summaryRangesOff(int[] nums) {
            List<String> ret = new ArrayList<>();
            int i = 0;
            int n = nums.length;
            while (i < n) {
                //记住low的位置
                int low = i;
                i++;
                while (i < n && nums[i] == nums[i - 1] + 1) {
                    i++;
                }
                int high = i - 1;
                StringBuilder temp = new StringBuilder(Integer.toString(nums[low]));
                if (low < high) {
                    temp.append("->");
                    temp.append(nums[high]);
                }
                ret.add(temp.toString());
            }
            return ret;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {0, 2, 3, 4, 6, 8, 9};
            System.out.println(solution.summaryRangesOff(arr));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
