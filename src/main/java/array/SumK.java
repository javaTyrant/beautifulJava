package array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2020/5/27
 */
public class SumK {
    //和可被 K 整除的子数组
    /*
        let A = [4,5,0,-2,-3,1], K = 5
        let map = {0: 1}
        let preSum = 0, count = 0

        循环A
        i = 0,  preSum = (0 + 4) % 5 = 4, map中不存在4 => map[4] = 1
        i = 1,  preSum = (4 + 5) % 5 = 4, map中存在4 => count += map[4] => count = 1,  map[4] = map[4] + 1 = 2
        i = 2,  preSum = (4 + 0) % 5 = 4, map中存在4 => count += map[4] => count = 3,  map[4] = 3
        i = 3,  preSum = (4 - 2) % 5 = 2, map中不存在2 => map[2] = 1
        i = 4,  preSum = (2 - 3) % 5 = -1 小于0 => preSum = -1 + 5 = 4, map中存在4 => count += map[4] => count = 6,  map[4] = 4
        i = 5,  preSum = (4 + 1) % 5 = 0, map中存在0 => count += map[0] => count = 7, map[0] = 2
    */
    public static void main(String[] args) {
        int[] arr = {4, 5, 0, -2, -3, 1};
        int[] arr1 = {-1, 2, 9};
        System.out.println(subarraysDivByK(arr, 5));
        System.out.println(subarraysDivByK(arr1, 2));
    }

    public static int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> record = new HashMap<>();
        record.put(0, 1);
        int sum = 0, ans = 0;
        for (int elem : A) {
            sum += elem;
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int modulus = (sum % K + K) % K;
            int same = record.getOrDefault(modulus, 0);
            ans += same;
            record.put(modulus, same + 1);
        }
        return ans;
    }

    public static int subarraysDivByKBest(int[] A, int k) {
        int[] m = new int[k];
        m[0] = 1;
        int count = 0, sum = 0;
        for (int a : A) {
            sum = (sum + a) % k;
            if (sum < 0) sum += k;
            count += m[sum];
            m[sum]++;
        }
        return count;
    }
}
