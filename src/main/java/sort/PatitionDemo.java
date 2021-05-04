package sort;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/7/6
 */
public class PatitionDemo {
    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 5, 6, 2};
        par(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void par(int[] arr, int l, int r) {
        int temp = arr[l];
        while (l < r) {
            while (l < r && arr[r] >= temp) r--;
            if (l < r) swap(arr, l, arr[r]);
            while (l < r && arr[l] < temp) l++;
            if (l < r) {
                swap(arr, r, arr[l]);
            }
        }
        swap(arr, l, temp);
    }

    private static void swap(int[] arr, int r, int i) {
        arr[r] = i;
    }
}
