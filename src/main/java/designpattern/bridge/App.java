package designpattern.bridge;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2021/2/27
 */
public class App {
    public static void main(String[] args) {
        //再也不用ifelse啦
        Color color = new Black();
        Shape shape = new Rectangle();
        shape.setColor(color);
        shape.draw();
        int[] arr = {2, 1, -2, 4, 5};
        //partition(arr, 0, arr.length - 1);
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int partition = partition(arr, lo, hi);
        sort(arr, lo, partition - 1);
        sort(arr, partition + 1, hi);
    }

    //快速排序partition
    private static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[lo];
        int left = lo;
        int right = hi;
        while (left < right) {
            while (left < right && arr[right] >= pivot) right--;
            if (left < right) arr[left] = arr[right];
            while (left < right && arr[left] < pivot) left++;
            if (left < right) arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }
}
