package binarysearch;

/**
 * @author lumac
 * @since 2020/7/13
 */
public class SearchInRotatedSortedArray {
    //33. 搜索旋转排序数组
    //这题一定要会写哦,晚上白版搞定他
    //你的算法时间复杂度必须是 O(log n) 级别。
    //优雅,该放弃的就放弃
    public static int search(int[] nums, int target) {
        int len = nums.length;
        //双指针
        int left = 0, right = len - 1;
        //
        while (left <= right) {
            //取中间
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
                //中间小于右边 ,说明mid右边是有序的
            } else if (nums[mid] < nums[right]) {
                //mid < target 且 target < 右边
                //说明target在右边,所以要改变left的值
                if (nums[mid] < target && target <= nums[right])
                    left = mid + 1;
                else
                    //target在左边
                    right = mid - 1;
                //中间大于右边,说明左边是有序的
            } else {
                //target在左边,要改变right的值
                if (nums[left] <= target && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        System.err.println(search(arr, 5));
    }


}
