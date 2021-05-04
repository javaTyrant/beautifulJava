package solution.leetcode.editor.cn;

public class LeetCode278 {
    //leetcode submit region begin(Prohibit modification and deletion)
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

    class VersionControl {
        boolean isBadVersion(int version) {
            return false;
        }
    }

    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int i = 0;
            int j = n;
            while (i <= n) {
                int mid = (j - i) / 2 + i;
                if (isBadVersion(mid)) {
                    j = mid;
                } else {
                    i = mid - 1;
                }
            }
            return i;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
