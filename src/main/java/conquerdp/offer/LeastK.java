package conquerdp.offer;

import java.util.Arrays;

/**
 * @author lufengxiang
 * @since 2021/6/5
 **/
public class LeastK {
    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 5, 7, 2};
        System.out.println(par(arr, 0, arr.length - 1));
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(getLeastNumbers(arr, 3)));
        int[] arr1 = {-8, -8, -9, -9};
        sort(arr1);
        System.out.println(Arrays.toString(arr1));
    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    //
    private static void sort(int[] arr, int low, int high) {
        if (low >= high) return;
        int par = par(arr, low, high);
        sort(arr, low, par - 1);
        sort(arr, par + 1, high);
    }

    //最小的k个数
    public static int[] getLeastNumbers(int[] arr, int k) {
        return null;
    }

    //1.把arr[low]的值放入一个位置,前面的值都小于low,后面的值都要大于它
    public static int par(int[] arr, int low, int high) {
        int l = low, h = high;
        int pivot = arr[low];
        while (l < h) {
            while (arr[h] >= pivot && l < h) {
                h--;
            }
            swap(arr, l, h);
            while (arr[l] < pivot && l < h) {
                l++;
            }
            swap(arr, l, h);
        }
        return l;
    }

    private static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }
}
