package solution.leetcode.editor.cn;

import java.util.ArrayList;

public class LeetCode395 {
    static class Solution1 {
        public static void main(String[] args) {
            System.out.println(longestSubstring("ababbc", 2));
        }

        static public int longestSubstring(String s, int k) {
            if (s == null || s.length() == 0 || s.length() < k) {
                return 0;
            }
            int len = s.length();
            if (k < 2) {
                return len;
            }
            return count(s.toCharArray(), k, 0, len - 1);
        }

        private static int count(char[] arr, int k, int left, int right) {
            if (right - left + 1 < k) {
                return 0;
            }
            int[] times = new int[26];
            for (int i = left; i <= right; i++) {
                times[arr[i] - 'a']++;
            }
            //左右逼近
            while (right - left + 1 >= k && times[arr[left] - 'a'] < k) {
                left++;
            }
            while (right - left + 1 >= k && times[arr[right] - 'a'] < k) {
                right--;
            }
            if (right - left + 1 < k) {
                return 0;
            }
            for (int i = left; i <= right; i++) {
                //从不满足的i开始找两边
                if (times[arr[i] - 'a'] < k) {
                    return Math.max(count(arr, k, left, i - 1), count(arr, k, i + 1, right));
                }
            }
            return right - left + 1;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.longestSubstring("ababab", 3));
            System.out.println(solution.longestSubstring1("bababccc", 3));
        }

        //至少有K个重复字符的最长子串
        //找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
        public int longestSubstring(String s, int k) {
            int[] count = new int[26];
            boolean[] isEnough = new boolean[26];
            char[] ch = s.toCharArray();
            for (char c : ch) if (++count[c - 'a'] >= k) isEnough[c - 'a'] = true;
            int l = 0, r = ch.length - 1;
            while (l < r && !isEnough[ch[l] - 'a']) l++;
            while (l < r && !isEnough[ch[r] - 'a']) r--;
            for (int i = l; i <= r; i++) {
                if (!isEnough[ch[i] - 'a'])
                    return Math.max(longestSubstring(s.substring(l, i), k), longestSubstring(s.substring(i + 1, r + 1), k));
            }
            return r - l + 1;
        }

        public int longestSubstring1(String s, int k) {
            int[] count = new int[26];
            //统计每个字符出现的次数
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }
            //
            ArrayList<Integer> split = new ArrayList<>();
            //
            //保存出现次数小于k的数据,也就是不满足的数据
            for (int i = 0; i < s.length(); i++) {
                if (count[s.charAt(i) - 'a'] < k) {
                    //存放的是索引位置
                    split.add(i);
                }
            }
            //如果没有小于k的次数,也就是都满足最小出现的次数
            if (split.isEmpty()) {
                return s.length();
            }
            //两个变量的意义何在
            int ans = 0, pre = 0;
            //有不满足的条件,为什么要加个length呢
            split.add(s.length());
            for (Integer i : split) {
                //ans
                System.out.println("pre:" + pre + " i:" + i);
                //i大于pre还要再递归一次,递归pre
                ans = i > pre ? Math.max(ans, longestSubstring1(s.substring(pre, i), k)) : ans;
                //pre赋值
                pre = i + 1;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
