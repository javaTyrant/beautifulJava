package sort;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020-05-09
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {2, 1, 3, -3, 7, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
        int[] arr1 = {1, 1, 1, 1, 1, 1};
        sort3way(arr1);
        System.out.println(Arrays.toString(arr1));
    }

    public static void sort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
    }

    static void qSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int p = partition(arr, low, high);
        qSort(arr, low, p - 1);
        qSort(arr, p + 1, high);
    }

    //分区
    private static int partition(int[] arr, int l, int r) {
        //先找一个pivot
        int temp = arr[l];
        while (l < r) {
            //找到arr[r]小于temp的
            while (l < r && arr[r] >= temp) r--;
            //交换
            if (l < r) arr[l] = arr[r];
            //找到arr[l]大于等于temp的
            while (l < r && arr[l] < temp) l++;
            //交换
            if (l < r) arr[r] = arr[l];
        }
        //l==r的时候退出循环
        arr[l] = temp;
        return l;
    }

    private static int partition4(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        int temp = a[lo];
        for (; ; ) {
            //
            while (a[++i] < temp) if (i == hi) break;
            while (a[--j] > temp) if (j == lo) break;
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    //三向归并
    private static void sort3way(int[] a) {
        quickThreeWay(a, 0, a.length - 1);
    }

    private static void quickThreeWay(int[] a, int l, int h) {
        if (h <= l) return;
        //维护三个指针
        int lt = l, i = l + 1, gt = h;
        int temp = a[l];
        while (i <= gt) {
            if (a[i] < temp) swap(a, lt++, i++);
            else if (a[i] > temp) swap(a, i, gt--);
            else i++;
        }
        quickThreeWay(a, l, lt - 1);
        quickThreeWay(a, gt + 1, h);
    }

    static void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
