package solution.leetcode.editor.cn;

public class LeetCode154 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //寻找旋转排序数组中的最小值 II
        public int findMin(int[] numbers) {
            int l = 0, r = numbers.length - 1;
            while (l < r) {
                int mid = ((r - l) / 2) + l;
                //只要右边比中间大，那右边一定是有序数组
                if (numbers[r] > numbers[mid]) {
                    r = mid;
                    //右边小于中间
                } else if (numbers[r] < numbers[mid]) {
                    l = mid + 1;
                    //去重
                } else r--;
            }
            return numbers[l];
        }

        public static void main(String[] args) {

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
