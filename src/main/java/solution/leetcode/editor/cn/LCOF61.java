package solution.leetcode.editor.cn;

public class LCOF61 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
        // 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，
        // 可以看成任意数字。A 不能视为 14。
        public static boolean isStraight(int[] nums) {
            int[] cards = new int[14];
            int max = 0, min = 14;

            for (int num : nums) {
                if (num == 0) continue;
                //重复情况判断,重复的情况直接返回
                if (cards[num]++ > 0) return false;
                //
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
            //
            return max - min < 5;
        }

        public static void main(String[] args) {
            int[] arr = {2, 3, 4, 5, 6};
            System.out.println(isStraight(arr));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
