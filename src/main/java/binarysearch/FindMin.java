package binarysearch;

/**
 * @author lumac
 * @since 2020-05-10
 */
public class FindMin {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 2, 3, 4, 5, 6, 7};
        int[] arr1 = {1, 2};
        int[] arr2 = {2, 1};
        System.out.println(findMin(arr));
        System.out.println(findMin(arr1));
        System.out.println(findMin(arr2));
    }

    public static int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else if (nums[mid] > nums[r]) {
                //为什么mid要加1呢,[2,1]这种会无限循环
                l = mid + 1;
            } else {
                return nums[mid];
            }
        }
        return nums[l];
    }
}
