package solution.leetcode.editor.cn;

public class LeetCode605 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            int countZero = 0;
            int addition = 0;
            for (int i = 0; i < flowerbed.length; i++) {
                if (flowerbed[i] == 0) {
                    if (i == 0) addition++;
                    if (i == flowerbed.length - 1) addition++;
                    countZero++;
                } else {
                    n -= (countZero - 1 + addition) / 2;
                    addition = 0;
                    countZero = 0;
                }
            }
            // 防止最后一堆0没处理
            n -= (countZero - 1 + addition) / 2;
            return n <= 0;
        }

        //这个太好懂了 flowerbed = [1,0,0,0,1], n = 2
        public boolean canPlaceFlowersEasy(int[] flowerbed, int n) {
            int count = 0;
            for (int i = 0; i < flowerbed.length; ) {
                if (flowerbed[i] == 1) {
                    i += 2;
                } else if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                    count++;
                    i += 2;
                } else {
                    //后一格是1直接跳三格
                    i += 3;
                }
            }
            return count >= n;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {1, 0, 0, 0, 1, 0, 0, 1};
            solution.canPlaceFlowersEasy(arr, 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
