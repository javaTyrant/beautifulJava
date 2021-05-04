package solution.leetcode.editor.cn;

public class LeetCode69 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution so = new Solution();
            System.out.println(so.mySqrt(65));
            System.out.println(so.mySqrt_(65));
            int[] arr = {5};
            System.out.println(so.search(arr, 5));
        }

        public int search(int[] nums, int target) {
            int i = 0;
            int j = nums.length - 1;
            while (i <= j) {
                int mid = (j - i) / 2 + i;
                if (nums[mid] < target) {
                    i = mid + 1;
                } else if (nums[mid] > target) {
                    j = mid - 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }

        public static int mySqrt_(int x) {
            int l = 0, r = x, ans = -1;
            while (l <= r) {
                //mid
                int mid = l + (r - l) / 2;
                if ((long) mid * mid <= x) {
                    ans = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return ans;
        }

        //二分法
        public int mySqrt(int x) {
            if (x == 0) {
                return 0;
            }
            if (x < 4) {
                return 1;
            }
            int left = 2;
            int right = x / 2;
            //关注如何逼近结果的
            while (left + 1 < right) {
                int num = (left + right) / 2;
                long sq = (long) num * num;
                if (x < sq) {
                    right = num;
                }
                if (x > sq) {
                    left = num;
                }
                if (x == sq) {
                    return num;
                }
            }
            return left;
        }

        public int mySqrtNewton(int x) {
            if (x == 0) {
                return 0;
            }

            double C = x, x0 = x;
            while (true) {
                double xi = 0.5 * (x0 + C / x0);
                if (Math.abs(x0 - xi) < 1e-7) {
                    break;
                }
                x0 = xi;
            }
            return (int) x0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
