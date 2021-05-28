package conquerdp;

import org.checkerframework.checker.units.qual.A;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lufengxiang
 * @since 2021/5/13
 **/
public class SubSum {
    //x = 2.00000, n = 10 快速幂.
    public double myPow(double x, int n) {
        double res = 1.0;
        //
        int a = 0, b = 0;
        for (int i = n; i != 0; i = i / 2) {
            if (i % 2 != 0) {
                a++;
                res *= x;
            }
            //每次都乘一次.
            b++;
            x *= x;
        }
        System.out.println(a + ":" + b);
        return n < 0 ? 1 / res : res;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        SubSum solution = new SubSum();
        System.out.println(solution.myPow(2, 77));
        System.out.println(solution.maxSubArray(nums));
        System.out.println(solution.reverseParentheses("i(love)u"));
    }

    //剑指 Offer 42. 连续子数组的最大和
    //输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
    //要求时间复杂度为O(n)。
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //如果前一个数小于0,那么肯定是不选的.
            nums[i] += Math.max(nums[i - 1], 0);
            //res选最大的
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    //1190. 反转每对括号间的子串
    public String reverseParentheses(String s) {
        Deque<String> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }


    //环形子数组的最大和
    public int maxSubarraySumCircular(int[] A) {
        int N = A.length;
        int ans = A[0], cur = A[0];
        for (int i = 1; i < N; ++i) {
            cur = A[i] + Math.max(cur, 0);
            ans = Math.max(ans, cur);
        }
        int[] rightsums = new int[N];
        rightsums[N - 1] = A[N - 1];
        for (int i = N - 2; i >= 0; --i)
            rightsums[i] = rightsums[i + 1] + A[i];
        // maxright[i] = max_{j >= i} rightsums[j]
        int[] maxright = new int[N];
        maxright[N - 1] = A[N - 1];
        for (int i = N - 2; i >= 0; --i)
            maxright[i] = Math.max(maxright[i + 1], rightsums[i]);

        int leftsum = 0;
        for (int i = 0; i < N - 2; ++i) {
            leftsum += A[i];
            ans = Math.max(ans, leftsum + maxright[i + 2]);
        }
        return ans;
    }
}
