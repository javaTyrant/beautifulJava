package solution.leetcode.editor.cn;

public class LeetCode14 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //编写一个函数来查找字符串数组中的最长公共前缀。
        //
        //如果不存在公共前缀，返回空字符串""。
        //
        //示例1:
        //
        //输入: ["flower","flow","flight"]
        //输出: "fl"
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) return "";
            //先记录第一个prefix
            String prefix = strs[0];
            //从第二个开始找
            for (int i = 1; i < strs.length; i++) {
                //如果不包含直接减少一位,循环
                while (strs[i].indexOf(prefix) != 0) {
                    //不断的缩减prefix
                    prefix = prefix.substring(0, prefix.length() - 1);
                    if (prefix.isEmpty()) return "";
                }
            }
            return prefix;
        }

        public static void main(String[] args) {
            String[] arr = {"flower", "flow", "flight"};
            Solution solution = new Solution();
            System.out.println(solution.longestCommonPrefix(arr));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
