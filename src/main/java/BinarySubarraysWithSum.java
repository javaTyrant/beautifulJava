import java.util.HashMap;
import java.util.Map;

/**
 * @author lufengxiang
 * @since 2021/7/8
 **/
public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        int[] nums = {1, 1, 0};
        int goal = 2;
        System.out.println(numSubarraysWithSum(nums, goal));
        int[] arr = {2, 2, -1, 3, -2, -2};
        System.out.println(subarraySum(arr, 2));
        int[] arr1 = {1, 2, 1, 2, 1};
        System.out.println(majorityElement(arr1));
    }

    //哈希表:原数组的前缀和和sum,子数组(i,j]的区间之和为goal,那么sum[j] - sum[i] = goal
    //hash表的作用:我们用哈希表记录每一种前缀和出现的次数
    //nums[i] 不是 0 就是 1
    //0 <= goal <= nums.length
    public static int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            //前缀和累加.
            sum += num;
            //如果前缀和+num = goal
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        return ret;
    }

    //和为k的子数组
    public static int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int num : nums) {
            pre += num;
            //pre - k
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    //
    public int numSubarraysWithSum1(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int ret = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            ret += left2 - left1;
            right++;
        }
        return ret;
    }

    //可能不存在
    public static int majorityElement(int[] nums) {
        int candidate = -1;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        int length = nums.length;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        return count * 2 > length ? candidate : -1;
    }
}
