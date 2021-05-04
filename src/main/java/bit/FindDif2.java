package bit;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/7/27
 */
public class FindDif2 {
    public static int[] singleNumbers(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        //本身的作用是得到最低位的1，
        int flag = sum & (-sum);
        int res1 = 0;
        for (int val : nums) {
            if ((flag & val) != 0) {
                res1 ^= val;
            }
        }
        // sum = a ^ b
        // res1 = a 那么 a ^ a ^ b = b
        return new int[]{res1, res1 ^ sum};
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4};
        System.out.println(Arrays.toString(singleNumbers(arr)));
    }
}
