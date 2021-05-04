package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LCOF51 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reversePairs(int[] nums) {
            if (nums == null || nums.length < 1)
                return 0;
            int[] copy = new int[nums.length];
            System.arraycopy(nums, 0, copy, 0, copy.length);

            return mergeSort(nums, copy, 0, nums.length - 1);
        }

        public int mergeSort(int[] nums, int[] copy, int start, int end) {
            if (start == end) {
                copy[start] = nums[start];
                return 0;
            }
            int middle = (start + end) >> 1;
            int leftcount = mergeSort(copy, nums, start, middle);
            int rightcount = mergeSort(copy, nums, middle + 1, end);
            int count = 0;
            int i = middle, j = end;
            int lastindex = end;
            while (i >= start && j >= middle + 1) {
                if (nums[i] > nums[j]) {
                    copy[lastindex--] = nums[i--];
                    count += j - middle;
                } else
                    copy[lastindex--] = nums[j--];
            }
            while (i >= start)
                copy[lastindex--] = nums[i--];
            while (j >= middle + 1)
                copy[lastindex--] = nums[j--];

            return count + leftcount + rightcount;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    private List<String> list = new ArrayList<>();

    public String[] permutation(String s) {
        perm(s.toCharArray(), 0, s.length() - 1);

        //list转数组
        return list.toArray(new String[0]);
    }

    private void perm(char[] seq, int curPos, int n) {
        //
        if (curPos == n) {
            list.add(new String(seq));
        } else {
            for (int i = curPos; i <= n; i++) {
                // 判断第i个元素是否在seq[curPos,i－1]中出现过,如果出现过就不用交换了
                if (!findSame(seq, curPos, i)) {
                    swap(seq, curPos, i);
                    perm(seq, curPos + 1, n);
                    swap(seq, i, curPos);
                }
            }
        }
    }

    private boolean findSame(char[] seq, int from, int candidate) {
        for (int j = from; j < candidate; j++) {
            if (seq[j] == seq[candidate]) {
                return true;
            }
        }
        return false;
    }

    private void swap(char[] seq, int i, int j) {
        char tmp = seq[i];
        seq[i] = seq[j];
        seq[j] = tmp;
    }
}
