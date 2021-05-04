package dp;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author lumac
 * @since 2020/6/24
 */
public class Sum3Closest {

    public static void main(String[] args) {

    }

    //三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //排序,一定要排序
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; ++i) {
            //极端值判断
            if (nums[i] > 0) return result;
            //如果这个i和之前的i相同,那么一定是重复的
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int cur = nums[i];
            int left = i + 1, right = length - 1;
            while (left < right) {
                int sum = cur + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> l1 = new ArrayList<>();
                    l1.add(cur);
                    l1.add(nums[left]);
                    l1.add(nums[right]);
                    result.add(l1);
                    //左指针,和右边的比
                    while (left < right && nums[left + 1] == nums[left]) {
                        ++left;
                    }
                    //右指针,和左边的比
                    while (left < right && nums[right - 1] == nums[right]) {
                        --right;
                    }
                    ++left;
                    --right;
                } else if (sum < 0) {
                    ++left;
                } else {
                    --right;
                }
            }
        }
        return result;
    }

    //最接近的三数之和
    public static int threeSumClosest(int[] nums, int target) {
        //要保存一个最接近的值
        int closet = Integer.MAX_VALUE;
        int res = 0;
        Arrays.sort(nums);
        //for循环套着while
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                // 2,3,4,5
                //target是1  sum = 10
                int dist = target - sum;
                //target - sum > 0 target > sum
                if (dist > 0) {
                    l++;
                } else if (dist < 0) {
                    r--;
                } else {
                    //返回这三个数的和
                    return sum;
                }
                //更新
                if (closet > Math.abs(dist)) {
                    //别忘了abs
                    closet = Math.abs(dist);
                    res = sum;
                }
            }
        }
        return res;
    }
}
