package solution.leetcode.editor.cn;

public class LeetCode316 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {

        public static void main(String[] args) {
            Solution solution = new Solution();
            //abcd
            System.out.println(solution.removeDuplicateLettersEasy("abcacda"));
            //acdb
            System.out.println(solution.removeDuplicateLettersEasy("cbacdcb"));
            //cadb
            System.out.println(solution.removeDuplicateLettersEasy("cbadb"));

        }

        public String removeDuplicateLettersEasy(String s) {
            boolean[] vis = new boolean[26];
            int[] num = new int[26];
            for (int i = 0; i < s.length(); i++) {
                num[s.charAt(i) - 'a']++;
            }

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                //如果没有处理过
                if (!vis[ch - 'a']) {
                    //sb长度大于0 且 sb.charAt(sb.length() - 1) > ch这个是什么意思
                    //如果先放了个c,但是后面遇到比c小的,c又重复了,要先把c删除了
                    while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                        //有重复的才删除,因为走到这步
                        if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                            //false
                            vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                            //删除字符
                            sb.deleteCharAt(sb.length() - 1);
                        } else {
                            break;
                        }
                    }
                    //处理完了
                    vis[ch - 'a'] = true;
                    sb.append(ch);
                }
                num[ch - 'a'] -= 1;
            }
            return sb.toString();
        }

        public String removeDuplicateLetters(String s) {
            int[] charCount = new int[128];
            int[] stack = new int[32];
            int stackLen;
            int[] stackCount = new int[128];

            for (char ch : s.toCharArray()) {
                charCount[ch]++;
            }

            stackLen = 0;
            for (char ch : s.toCharArray()) {
                charCount[ch]--;

                // do not add duplicated char
                if (stackCount[ch] != 0) {
                    continue;
                }

                // remove old bigger char when it can still be added latter
                while (stackLen > 0 && stack[stackLen - 1] > ch && charCount[stack[stackLen - 1]] > 0) {
                    stackCount[stack[stackLen - 1]]--;
                    stackLen--;
                }

                // add current char
                stack[stackLen] = ch;
                stackLen++;
                stackCount[ch]++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < stackLen; i++) {
                sb.append((char) stack[i]);
            }

            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
