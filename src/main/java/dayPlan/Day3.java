package dayPlan;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lumac
 * @since 2021/2/25
 */
public class Day3 {
    //402. 移掉K位数字.思路太牛逼了
    public String removeKdigits(String num, int k) {
        //如果num的长度==k,全部都要删掉,直接返回0
        if (num.length() == k) {
            return "0";
        }
        StringBuilder s = new StringBuilder(num);
        //需要找几次
        for (int i = 0; i < k; i++) {
            //idx = 0
            int idx = 0;
            //找到j<j-1位置的数,记录索引
            for (int j = 1; j < s.length() && s.charAt(j) >= s.charAt(j - 1); j++) {
                idx = j;
            }
            s.delete(idx, idx + 1);
            //删除前置0
            while (s.length() > 1 && s.charAt(0) == '0') {
                s.delete(0, 1);
            }
        }
        return s.toString();
    }

    //栈的思路
    public String removeKdigitsStack(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            //
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }

        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }

    //盛水最多的容器
    public int maxArea(int[] height) {
        //双指针
        int left = 0, right = height.length - 1;
        //结果
        int res = 0;
        while (left <= right) {
            //选左右最短
            int h = Math.min(height[left], height[right]);
            //选最大的
            res = Math.max(res, h * (right - left));
            //哪边需要继续执行
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        //返回res
        return res;
    }

    //300. 最长递增子序列,这种思路面试太难想到了
    public int lengthOfLIS(int[] nums) {
        //以i结束的最长上升子序列
        int[] tails = new int[nums.length];
        //长度k
        int res = 0;
        for (int num : nums) {
            //双指针
            int i = 0, j = res;
            while (i < j) {
                int m = (i + j) / 2;
                if (tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if (res == j) res++;
        }
        return res;
    }

    //时间复杂度O(n^2)
    public int lengthOfLISEasy(int[] nums) {
        //参数校验
        if (nums.length == 0) {
            return 0;
        }
        //dp数组
        int[] dp = new int[nums.length];
        //dp[0] = 1
        dp[0] = 1;
        //最大长度至少是1
        int maxans = 1;
        //遍历
        for (int i = 1; i < nums.length; i++) {
            //dp[i] = 1
            dp[i] = 1;
            //从区间查找
            for (int j = 0; j < i; j++) {
                //如果i的位置大于
                //大于的话
                if (nums[i] > nums[j]) {
                    //选一个最大的,dp[i]和dp[j]+1的最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //选大的作为结果
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    //977. 有序数组的平方
    public int[] sortedSquaresMergeSort(int[] nums) {
        int n = nums.length;
        int negative = -1;
        //找到负数和正数的交界点
        for (int i = 0; i < n; ++i) {
            if (nums[i] < 0) {
                negative = i;
            } else {
                break;
            }
        }
        //归并排序
        int[] ans = new int[n];
        int index = 0, i = negative, j = negative + 1;
        while (i >= 0 || j < n) {
            if (i < 0) {//负数用完了,直接取正数
                ans[index] = nums[j] * nums[j];
                ++j;
            } else if (j == n) {
                //正数用完了,直接去负数
                ans[index] = nums[i] * nums[i];
                --i;
            } else if (nums[i] * nums[i] < nums[j] * nums[j]) {
                //取大的负数
                ans[index] = nums[i] * nums[i];
                --i;
            } else {
                //取大的正数
                ans[index] = nums[j] * nums[j];
                ++j;
            }
            ++index;
        }

        return ans;
    }

    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int j = 0;
        while (j < N && A[j] < 0)
            j++;
        int i = j - 1;

        int[] ans = new int[N];
        int t = 0;

        while (i >= 0 && j < N) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }

        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }
        while (j < N) {
            ans[t++] = A[j] * A[j];
            j++;
        }

        return ans;
    }

    //每日温度
    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            int temperature = T[i];
            //牛逼啊
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                //pop得到之前的索引
                int prevIndex = stack.pop();
                //i - preIndex = 天数
                ans[prevIndex] = i - prevIndex;
            }
            //把当前的push进去
            stack.push(i);
        }
        //未处理的就是0
        return ans;
    }

    public double myPow(double x, int n) {
        double res = 1.0;
        //如何处理循环,从n开始,每次÷2
        for (int i = n; i != 0; i /= 2) {
            //奇数 = 乘一次,最后一次肯定是1,拼接答案,如果是奇数的话会调用两次
            if ((i % 2) != 0) {
                res *= x;
            }
            //就变成偶数了
            x *= x;
        }
        //判断是否是负数,负数是1÷res
        return n < 0 ? 1 / res : res;
    }

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        System.out.println(day3.myPow(2, 9));
        System.out.println(day3.removeKdigits("25399", 2));
        int[] arr = {1, 2, -1, 3, -2, 4, 5};
        int[] arr1 = {-4, -1, 0, 3, 10};
        int[] daily = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(day3.dailyTemperatures(daily)));
        System.out.println(Arrays.toString(day3.sortedSquaresMergeSort(arr1)));
        System.out.println(day3.lengthOfLIS(arr));
    }
}
