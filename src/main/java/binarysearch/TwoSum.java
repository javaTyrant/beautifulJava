package binarysearch;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2020/5/21
 */
public class TwoSum {
    public static void main(String[] args) {
        Double d1 = new Double(-1.0 / 0.0);
        Double d2 = new Double(0.0 / 0.0);
        System.out.println(d1 + " is:" + Double.isNaN(d1));
        System.out.println(d2 + " is:" + Double.isNaN(d2));
        System.out.println(1 / 0);
        System.out.println(1.0 / 0);
    }

    public int[] twoSumB(int[] nums, int target) {
        //
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            //值做key
            map.put(nums[i], i);
        }
        return new int[0];
    }

    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int[] res = new int[2];
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                res[0] = nums[left];
                res[1] = nums[right];
                return res;
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];

    }
}
