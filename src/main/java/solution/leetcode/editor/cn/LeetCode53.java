package solution.leetcode.editor.cn;

public class LeetCode53 {
    class Solution1 {
        public class Status {
            public int lSum, rSum, mSum, iSum;

            public Status(int lSum, int rSum, int mSum, int iSum) {
                this.lSum = lSum;
                this.rSum = rSum;
                this.mSum = mSum;
                this.iSum = iSum;
            }
        }

        public int maxSubArray(int[] nums) {
            return getInfo(nums, 0, nums.length - 1).mSum;
        }

        public Status getInfo(int[] a, int l, int r) {
            if (l == r) {
                return new Status(a[l], a[l], a[l], a[l]);
            }
            int m = (l + r) / 2;
            Status lSub = getInfo(a, l, m);
            Status rSub = getInfo(a, m + 1, r);
            return pushUp(lSub, rSub);
        }

        public Status pushUp(Status l, Status r) {
            int iSum = l.iSum + r.iSum;
            int lSum = Math.max(l.lSum, l.iSum + r.lSum);
            int rSum = Math.max(r.rSum, r.iSum + l.rSum);
            int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
            return new Status(lSum, rSum, mSum, iSum);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //最大子序和
        public int maxSubArray(int[] nums) {
            if (nums.length == 0) return 0;
            //res为最小值
            int res = Integer.MIN_VALUE;
            int maxsum = 0;
            for (int num : nums) {
                //取0和max较大值累加num
                maxsum = Math.max(0, maxsum) + num;
                //res
                res = Math.max(res, maxsum);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
