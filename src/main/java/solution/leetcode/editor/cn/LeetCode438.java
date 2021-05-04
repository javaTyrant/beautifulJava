package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode438 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            solution.findAnagrams("cbaebabacd", "abc");
        }

        //找到字符串中所有字母异位词
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> ans = new ArrayList<>();
            if (s == null || s.length() == 0 || s.length() < p.length()) {
                return ans;
            }
            char[] strP = p.toCharArray();
            char[] strS = s.toCharArray();
            //为什么要123 z = 122,97之前的肯定是用不到的
            int[] chS = new int[123];
            int[] chP = new int[123];
            //将p的字符串放进索引表里,同时将相同长度的s的字符也放进索引表里
            for (int i = 0; i < strP.length; i++) {
                chP[strP[i]]++;
                chS[strS[i]]++;
            }
            //开始遍历处理,之前已经处理的p长度的字符
            for (int i = 0; i <= strS.length - strP.length; i++) {
                boolean flag = true;
                //什么意思?i=0的时候,说明已经添加了一轮,要先把之前的一轮处理完
                //所以不需要走这个逻辑,在上个for循环中处理过了,只需要判断
                //i>0的时候,s的长度大于p,所以还需要控制指针
                if (i > 0) {
                    //cbaebabacd
                    //i = 1 chS[strS[0]='c' = 99] --
                    //相当于指针后移一位,相当关键的两步
                    chS[strS[i - 1]]--;
                    //继续往hash表里添加数据
                    chS[strS[i - 1 + strP.length]]++;
                }
                //判断索引位是否相等
                for (char j = 'a'; j <= 'z'; j++) {
                    if (chS[j] != chP[j]) {
                        flag = false;
                        break;
                    }
                }
                //满足
                if (flag) {
                    ans.add(i);
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
