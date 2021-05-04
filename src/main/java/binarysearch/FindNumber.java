package binarysearch;

/**
 * @author lumac
 * @since 2020-05-21
 */
public class FindNumber {
    public static void main(String[] args) {
        int[] arr = {5, 7, 7, 8, 8, 10};
        int[] arr2 = {5, 7, 7, 8, 8, 10};
        System.out.println(search(arr2, 6));
        System.out.println(search(arr, 5));
        int[] arr1 = {1};
        System.out.println(search(arr1, 1));
        System.out.println(search(arr1, 2));

    }

    //bad try
    public static int search(int[] nums, int target) {
        int res = 0;
        int left = 0;
        int right = nums.length - 1;
        if (nums == null || nums.length == 0) {
            return res;
        }
        while (left <= right) {
            int mid = (right + left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                res++;
                left++;
                right--;
            }
        }
        return res;
    }
}
