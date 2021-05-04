package dayPlan;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author lumac
 * @since 2021/3/14
 */
public class Day13 {
    public int getNumberOfBacklogOrders(int[][] orders) {
        return 0;
    }

    public int maxAscendingSum(int[] nums) {
        int ans = 0, sum = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    //90. 64位整数乘法
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long p = in.nextLong();
        long res = 0;
        while (b > 0) {
            if ((b & 1) > 0) {
                res = (res + a) % p;
            }
            b >>= 1;
            a = a * 2 % p;
        }
        System.out.println(res);

    }

    //a*b % p


    //1 <= s.length <= 3 * 105
    //s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
    //s 表示一个有效的表达式
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        // sign 代表正负
        int sign = 1, res = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            //如果是数字
            if (Character.isDigit(ch)) {
                int cur = ch - '0';
                //把数字全部截取出来
                while (i + 1 < length && Character.isDigit(s.charAt(i + 1)))
                    cur = cur * 10 + s.charAt(++i) - '0';
                //累加
                res = res + sign * cur;
                //如果是加号
            } else if (ch == '+') {
                sign = 1;
                //如果是减号
            } else if (ch == '-') {
                sign = -1;
                //左括号
            } else if (ch == '(') {
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
                //右括号
            } else if (ch == ')') {
                //弹出符号 * res + res
                res = stack.pop() * res + stack.pop();
            }
        }
        return res;
    }
}
