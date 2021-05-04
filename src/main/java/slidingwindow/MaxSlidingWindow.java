package slidingwindow;

/**
 * @author lumac
 * @since 2020/7/25
 */
public class MaxSlidingWindow {
    //239. 滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;
        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);
            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }
        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }

    public int[] maxSlidingWindowBest(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        //答案的长度
        int ansLen = nums.length - k + 1;
        //返回数组
        int r[] = new int[ansLen];
        //窗口内的最大值
        int max = Integer.MIN_VALUE;
        //窗口内最大值的指针
        int p = -1;
        for (int i = 0; i < k; i++) {
            if (nums[i] > max) {
                p = i;
                max = nums[i];
            }
        }
        //答案的第一个值肯定是max
        r[0] = max;
        //双指针
        int last = k - 1;
        int start = 0;
        for (int j = 1; j < r.length; j++) {
            start++;
            last++;
            //[2,3,1,4]
            if (p >= start) {
                //只需要和最后一个比,因为前面的实际上是比较过了
                if (nums[last] >= nums[p]) {
                    p = last;
                    r[j] = nums[last];
                } else {
                    r[j] = nums[p];
                }
            } else {
                //p <= start
                if (nums[last] >= nums[p]) {
                    p = last;
                    r[j] = nums[last];
                } else {
                    //rebase
                    p = last;
                    r[j] = nums[last];
                    for (int m = start; m < last; m++) {
                        if (nums[m] > r[j]) {
                            r[j] = nums[m];
                            p = m;
                        }
                    }
                }

            }
        }

        return r;
    }
}
