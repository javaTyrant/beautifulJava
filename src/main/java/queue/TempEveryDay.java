package queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author lumac
 * @since 2020/6/11
 */
public class TempEveryDay {
    public static int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            //想明白哈,栈不为空,且当前的值,大于栈顶的值
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                //删除栈顶,接着循环比较
                int prevIndex = stack.pop();
                //取出索引,放入答案集合
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
    }
}
