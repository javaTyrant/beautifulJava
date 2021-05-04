package solution.leetcode.editor.cn;

import java.util.LinkedList;

public class LeetCode71 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static String simplifyPath(String path) {
            //利用栈或队列来解决
            //遇到..就把栈里面的移除掉
            LinkedList<String> queue = new LinkedList<>();
            String[] paths = path.split("/");
            for (String p : paths) {
                //过滤掉
                if ("".equals(p) || ".".equals(p)) {
                    continue;
                    //遇到..remove掉
                } else if ("..".equals(p)) {
                    if (!queue.isEmpty()) {
                        queue.removeLast();
                    }
                    //否则入队列
                } else {
                    queue.addLast("/" + p);
                }
            }
            //
            if (queue.isEmpty()) {
                return "/";
            } else {
                //出栈拼接
                StringBuilder res = new StringBuilder();
                while (!queue.isEmpty()) {
                    res.append(queue.removeFirst());
                }
                return res.toString();
            }
        }

        public static void main(String[] args) {
            System.out.println(simplifyPath("/a/./b/../../c/"));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
