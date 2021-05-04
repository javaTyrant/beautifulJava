package solution.leetcode.editor.cn;

public class LeetCode135 {
    //leetcode submit region begin(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 2, 1, 0};
        System.out.println(solution.candy(arr));
    }

    //我们的策略是:
    //1.从左往右,如果当前的比之前的多,加一颗糖,否则给1颗
    //2.从右往左,为什么还需要从右往左呢[1,2,1,0],从左往右只能处理递增的情况
    //如果是这个用例,第一轮是[1,2,1,1]
    //从右往左的策略是:如果当前的大于后面的,right++,否则为1
    static class Solution {
        //1.每个孩子至少分配到 1 个糖果。
        //2.相邻的孩子中，评分高的孩子必须获得更多的糖果。
        public int candy(int[] ratings) {
            int n = ratings.length;
            int[] left = new int[n];
            //从左到右遍历
            for (int i = 0; i < n; i++) {
                //大于之前多发一颗糖
                if (i > 0 && ratings[i] > ratings[i - 1]) {
                    left[i] = left[i - 1] + 1;
                } else {
                    //否则发1
                    left[i] = 1;
                }
            }
            int right = 0, ret = 0;
            //从右向左
            for (int i = n - 1; i >= 0; i--) {
                //right初始肯定是1
                if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                    right++;
                } else {
                    right = 1;
                }
                //取大的值
                ret += Math.max(left[i], right);
            }
            return ret;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
