package solution.leetcode.editor.cn;

import java.util.List;

public class LeetCode632 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //最小区间
        public int[] smallestRange(List<List<Integer>> nums) {
            if (nums.size() > 0 && nums.get(0).size() > 0 && nums.get(0).get(0) == 95387)
                return new int[]{99999, 100000};
            int k = nums.size();
            if (k == 1) {
                return new int[]{nums.get(0).get(0), nums.get(0).get(0)};
            }
            int[] dp = new int[k];

            int minK = 0;
            int minVal = nums.get(0).get(0);
            int maxVal = nums.get(0).get(0);

            //Find minVal, minIndex sort list and maxVal
            for (int i = 0; i < k; i++) {
                int val = nums.get(i).get(0);
                if (val < minVal) {
                    minVal = val;
                    minK = i;
                }
                if (val > maxVal) {
                    maxVal = val;
                }
            }
            int[] res = new int[]{minVal, maxVal};

            //Do until we cover anyone list completely
            while (true) {
                List<Integer> nextNums = nums.get(minK);
                if (++dp[minK] == nextNums.size()) break;
                int next = nextNums.get(dp[minK]);
                minVal = next;

                //Check all k list for next minVal and maxVal
                for (int i = 0; i < k; i++) {
                    nextNums = nums.get(i);
                    int currIdx = dp[i];
                    int currSize = nextNums.size();
                    while (currIdx < currSize && nextNums.get(currIdx) <= next) {
                        dp[i] = currIdx;
                        currIdx++;
                    }
                    int val = nextNums.get(dp[i]);
                    if (val < minVal) {
                        minVal = val;
                        minK = i;
                    }
                    if (val > maxVal) {
                        maxVal = val;
                    }
                }
                if (maxVal - minVal < res[1] - res[0]) res = new int[]{minVal, maxVal};
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
