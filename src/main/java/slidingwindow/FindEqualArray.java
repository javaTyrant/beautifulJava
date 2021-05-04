package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2020-05-15
 */
public class FindEqualArray {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1};
        System.out.println(subarraySum(arr, 2));
        int i = 1;
        int j = 1;
        //i先加再复制给a
        int a = ++i;
        //j先赋值给b,再执行++
        int b = j++;
        System.out.println("j=" + j);
        System.out.println("a=" + a);
        System.out.println("b=" + b);
        System.out.println(subarraySumBrute(arr, 2));
    }

    /**
     * [1,2,1,2] 3
     * 如何优化呢
     * 这个方法的痛点是什么呢,是不是多做了很多重复的计算呢
     */
    public static int subarraySumBrute(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            //j >= 0
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int subarraySum(int[] nums, int k) {
        /*
         扫描一遍数组, 使用map记录出现同样的和的次数, 对每个i计算累计和sum并判断map内是否有sum-k
         */
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ret = 0;

        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                ret += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ret;
    }
}
