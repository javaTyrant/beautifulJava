package binarysearch;

/**
 * @author lumac
 * @since 2020-05-26
 */
public class FindDuplicate {
    public static void main(String[] args) {
        int[] arr = {2, 5, 9, 6, 9, 3, 8, 9, 7, 1};
        System.out.println(findDuplicate(arr));
        System.out.println(findDuplicateSlowFast(arr));
    }

    public static int findDuplicateSlowFast(int[] nums) {
        //一阶段
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            //牛逼啊,
            fast = nums[nums[fast]];
        } while (slow != fast);
        //二阶段
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    public static int findDuplicateBinary(int[] nums) {
        int n = nums.length, ans = 0;
        int bit_max = 31;
        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }
}
