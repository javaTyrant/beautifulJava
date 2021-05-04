import java.util.Arrays;
import java.util.HashMap;

/**
 * @author lumac
 * @since 2020/6/27
 */
public class FirstMissingPositive {
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static int firstMissingPositiveAnother(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            //i == a[i] - 1意味着什么呢
            while (a[i] >= 1 && a[i] <= n && i != a[i] - 1) {
                int left = i, right = a[i] - 1;
                //终止
                if (a[left] == a[right]) break;
                //交换左右
                int tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;
            }
        }
        System.out.println(Arrays.toString(a));
        //找到特定的值
        for (int i = 0; i < n; i++) {
            // System.out.print(a[i] + " ");
            if (i + 1 != a[i]) return i + 1;
        }
        return n + 1;
    }

    //如果数组中包含x∈[1,N]，那么恢复后，数组的第x-1个元素为x
    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 1, -8};
        int[] arr1 = {3, 4, 2, 1, -8};
        System.out.println(firstMissingPositive(arr));
        System.out.println(firstMissingPositiveAnother(arr1));
        HashMap<Integer, Integer> i = new HashMap<>();
        i.put(1, 1);
        i.put(2, 1);
        i.put(3, 1);
        i.put(4, 1);
        i.put(5, 1);
        i.put(6, 1);
        i.put(7, 1);
        i.put(8, 1);
        i.put(9, 1);
        i.put(10, 1);
        i.put(11, 1);
        i.put(12, 1);
        i.put(13, 1);
    }
}
