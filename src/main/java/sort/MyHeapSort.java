package sort;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2021/2/27
 */
public class MyHeapSort {
    //如何bugfree写出来呢?索引的细节一定要弄清楚呢
    public static void main(String[] args) {
        MyHeapSort sort = new MyHeapSort();
        //先实现一个方法,可以把数组中最大的数字放到第一个,既大顶
        int[] arr = {1, 2, 4, 5, 6, 0};
//        for (int k = arr.length / 2; k >= 1; k--) {
//            sort.sink(arr, k, arr.length);
//        }
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
        //然后把最大的和数组末位交换,然后数组长度缩小1,继续执行第一步操作
    }

    public void sort(int[] arr) {
        int len = arr.length;
        for (int i = len / 2; i >= 1; i--) {
            sink(arr, i, len);
        }
        int n = arr.length;
        while (n > 1) {
            change(arr, n--, 1);
            sink(arr, 1, n);
        }
    }

    public void sink(int[] arr, int lo, int high) {
        while (2 * lo <= high) {
            int j = 2 * lo;
            if (j < high && arr[j - 1] < arr[j]) j++;
            if (arr[lo - 1] > arr[j - 1]) break;
            change(arr, lo, j);
            lo = j;
        }
    }

    public void change(int[] arr, int i, int j) {
        int temp = arr[i - 1];
        arr[i - 1] = arr[j - 1];
        arr[j - 1] = temp;
    }
}
