package sort;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/6/19
 */
public class GetLeastNumbers {
    //最小的k个数,o(n)的复杂度
    public int[] getLeastNumbers(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        //[2,1,3,5,-9] 2
        //-9,1
        //二分法
        while (low < high) {
            int i = partition(arr, low, high);
            if (i >= k) {
                high = i - 1;
            }
            if (i <= k) {
                low = i + 1;
            }
        }
        return Arrays.copyOf(arr, k);
    }

    public int getLeastNumbersK(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        //[2,1,3,5,-9] 2
        //-9,1
        //二分法
        while (low < high) {
            int i = partition(arr, low, high);
            if (i >= k) {
                high = i - 1;
            }
            if (i <= k) {
                low = i + 1;
            }
        }
        return arr[k - 1];
    }

    int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while (low < high) {
            //高指针--
            while (low < high && nums[high] >= pivot)
                high--;
            //此时nums[high]<pivot,交换一下复制
            nums[low] = nums[high];
            //处理低指针
            while (low < high && nums[low] <= pivot)
                low++;
            //此时nums[low] > pivot
            nums[high] = nums[low];
        }
        //最后交换一下
        nums[low] = pivot;
        //返回low指针
        return low;
    }

    public static void main(String[] args) {
        GetLeastNumbers g = new GetLeastNumbers();
        int[] arr = {4, 1, 3, 5, -9};
        int[] numbers = g.getLeastNumbers(arr, 2);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(numbers));
        System.out.println(g.getLeastNumbersK(arr, 3));
    }
}
