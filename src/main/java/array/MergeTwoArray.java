package array;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/6/2
 */
public class MergeTwoArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 0, 0, 0};
        int[] arr1 = {2, 5, 6};
        merge(arr, 3, arr1, 3);
        System.out.println(Arrays.toString(arr));
        int x = 8;
        int y = 8;
        //先使用再--
        System.out.println(x--);
        System.out.println(x);
        //先--再使用
        System.out.println(--y);
    }

    //m,n可千万别导致数组越界哦
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length - 1;
        while (n > 0 || m > 0) {
            if (n == 0) {
                nums1[len] = nums1[m - 1];
                m--;
                len--;
            } else if (m == 0) {
                nums1[len] = nums2[n - 1];
                n--;
                len--;
            } else if (nums1[m - 1] > nums2[n - 1]) {
                nums1[len--] = nums1[m - 1];
                m--;
            } else {
                nums1[len--] = nums2[n - 1];
                n--;
            }
        }
    }

    //一对比显得上面的好累赘
    public void mergeElegant(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while ((p1 >= 0) && (p2 >= 0)) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
