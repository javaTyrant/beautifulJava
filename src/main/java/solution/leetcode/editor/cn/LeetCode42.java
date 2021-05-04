package solution.leetcode.editor.cn;

public class LeetCode42 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //接雨水
        public int trap(int[] height) {
            //需要几个变量呢
            //res
            //左边的最大值,右边的最大值
            int res = 0, maxLeft = 0, maxRight = 0;
            //两个指针
            int left = 0, right = height.length - 1;
            //找短板,左边小处理左边,右边小处理右边
            while (left < right) {
                //如果左边的最大值 < 右边的
                if (maxLeft < height[right]) {
                    //是否要更新maxLeft
                    if (height[left] > maxLeft) {
                        maxLeft = height[left];
                    } else {
                        //用左边最大-高度
                        res += maxLeft - height[left];
                        left++;
                    }
                } else {//右边是相同的逻辑
                    if (maxRight < height[right]) {
                        maxRight = height[right];
                    } else {
                        res += maxRight - height[right];
                        right--;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
