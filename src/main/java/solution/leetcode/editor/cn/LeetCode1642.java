package solution.leetcode.editor.cn;

public class LeetCode1642 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {

        }

        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            int n = heights.length;
            if (n == 1)
                return 0;
            int i = 1;
            for (; i < n; i++) {
                //求出需要多少砖块
                int needsBracks = heights[i] - heights[i - 1];
                //不需要
                if (needsBracks <= 0)
                    continue;
                //先用砖块,再用梯子
                if (bricks >= needsBracks) {
                    bricks -= needsBracks;
                } else {
                    if (ladders > 0) {
                        ladders--;
                    } else {
                        break;
                    }
                }
            }
            return i - 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
