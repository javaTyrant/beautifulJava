/**
 * @author lumac
 * @since 2020/11/21
 */
public class ClostThreeSum {
    public static int search(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i - 1) / 2;
            if (nums[mid] > target) {
                j = mid - 1;
            } else if (nums[mid] < target) {
                i = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search1(arr, 0));
    }
}
