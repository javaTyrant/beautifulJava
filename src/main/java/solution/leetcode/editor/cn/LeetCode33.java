package solution.leetcode.editor.cn;

public class LeetCode33 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] nums, int target) {
            int len = nums.length;
            int left = 0, right = len - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target)
                    return mid;
                    //中间<右边:中间到右边是有序的
                else if (nums[mid] < nums[right]) {
                    //targer在mid和right直接
                    if (nums[mid] < target && target <= nums[right])
                        //nums[mid]是小于target的,收缩一次
                        left = mid + 1;
                    else//target不在有序的数组直接,那么找左边的.mid为什么要减1,mid肯定是不等于target的,所以-1
                        right = mid - 1;
                } else {//中间大于右边:左边是有序的
                    //如果target在左边有序的之间,右边的可以放弃了
                    if (nums[left] <= target && target < nums[mid])
                        right = mid - 1;
                    else//否则放弃左边
                        left = mid + 1;
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
