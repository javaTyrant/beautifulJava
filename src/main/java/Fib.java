import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/7/25
 */
public class Fib {
    public static int fib_(int n) {
        if (n <= 1) {
            return n;
        }
        int[] cache = new int[n + 1];
        cache[1] = 1;
        for (int i = 2; i <= n; i++) {
            //其实只需要数组的两个元素
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[n];
    }

    public static int fib(int n) {
        int pre = 0;
        int cur = 1;
        int res = 0;
        for (int i = 0; i <= n; i++) {
            //其实只需要数组的两个元素
            res += pre;
            pre = cur;
            cur = res;
        }
        return res;
    }

    public static int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int i = 0, j = nums.length - 1;
        while (i < j) {
            while (i < j && nums[i] % 2 != 0) i++;
            while (i < j && nums[j] % 2 == 0) j--;
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        // 0 1 1 2 3 5 8 13
        System.out.println(fib(0));
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
        System.out.println(fib(5));
        int[] arr = {1, 3, 5};
        System.out.println(Arrays.toString(exchange(arr)));
    }
}
