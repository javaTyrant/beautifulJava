package solution.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class LeetCode128 {
    //leetcode submit region begin(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {100, 4, 200, 1, 3, 2};
        solution.longestConsecutive(arr);
    }

    static class Solution {
        //最长连续序列 简单啊
        public int longestConsecutive(int[] nums) {
            Set<Integer> numsSet = new HashSet<>();
            for (Integer num : nums) {
                numsSet.add(num);
            }
            int longest = 0;
            for (Integer num : nums) {
                if (numsSet.remove(num)) {
                    // 向当前元素的左边搜索,eg: 当前为100, 搜索：99，98，97,...
                    int currentLongest = 1;
                    int current = num;
                    //向左边搜索第一个是100的话,如果有99会remove成功的
                    while (numsSet.remove(current - 1)) {
                        current--;
                    }
                    //更新值
                    currentLongest += (num - current);
                    // 向当前元素的右边搜索,eg: 当前为100, 搜索：101，102，103,...
                    //复原向右搜索
                    current = num;
                    while (numsSet.remove(current + 1)) {
                        current++;
                    }
                    currentLongest += (current - num);
                    // 搜索完后更新longest.
                    //把结果保存给最长的值,因为currentLongest只保存当前数字搜索到的最长的值
                    longest = Math.max(longest, currentLongest);
                }
            }
            return longest;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
