package solution.leetcode.editor.cn;

public class LeetCode1232 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkStraightLine(int[][] coordinates) {
            if (coordinates.length <= 2) {
                return true;
            }
            int deltaX = coordinates[1][0] - coordinates[0][0];
            int deltaY = coordinates[1][1] - coordinates[0][1];
            for (int i = 2; i < coordinates.length; i++) {
                int deltaX1 = coordinates[i][0] - coordinates[0][0];
                int deltaY1 = coordinates[i][1] - coordinates[0][1];
                if (deltaX * deltaY1 != deltaY * deltaX1) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
