package solution.leetcode.editor.cn;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/12/17
 */
public class LCOF45 {
    //把数组排成最小的数
    //排序,重点在于比较的思路,比较"a + b" 与 "b + a"
    public String minNumber(int[] nums) {
        System.out.println(Arrays.toString(nums));
        sort(nums, 0, nums.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int i : nums) sb.append(i);
        return sb.toString();
    }

    //模板代码
    void sort(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    //主要的逻辑:把low放到合适的位置,两种partition的区别之处呢
    int partition(int[] a, int lo, int hi) {
        int j = lo;
        for (int i = lo + 1; i <= hi; i++) {
            if (compare(a[i], a[lo]) < 0) {
                int m = ++j;
//                System.out.println(i + ":" + m);
                swap(a, i, m);
//                System.out.println(Arrays.toString(a));
            }
        }
//        System.out.println(lo + ":" + j);
        swap(a, lo, j);
//        System.out.println(Arrays.toString(a));
        return j;
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    //替换下就可以返回最大了
    //比较ab相连和ba相连的最大值
    int compare(int a, int b) {
        // 这行注掉会有这样的错误
        //[1,2,3,4,5,6,7,8,9,0]
        // wa "1234567890" expect "0123456789"
        if (a == 0 || b == 0) return a - b; //比较0的特殊性
        return add(a, b) - add(b, a);
    }

    //妙,太妙了
    int add(int a, int b) {
        int n = 1, bb = b;
        while (b != 0) {
            n *= 10;
            b /= 10;
        }
        return a * n + bb;
    }

    //输入: [3,30,34,5,9]
    //输出: "3033459"
    public static void main(String[] args) {
        LCOF45 solution = new LCOF45();
        int[] a = {1, 2, 3};
        int[] b = {2, 1, 3};
        int[] c = {3, 2, 1};
        System.out.println(solution.partition(a, 0, a.length - 1));
        System.out.println(Arrays.toString(a));
        System.out.println(solution.partition(b, 0, b.length - 1));
        System.out.println(Arrays.toString(b));
        System.out.println(solution.partition(c, 0, c.length - 1));
        System.out.println(Arrays.toString(c));
        System.out.println();
        System.out.println(solution.add(23, 32));
        int[] arr = {8, 9, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println(solution.minNumber(arr));
        System.out.println(solution.minNumberAnother(arr));
    }

    //主要的逻辑:把low放到合适的位置
    int partition1(int[] a, int lo, int hi) {
        int j = lo;
        for (int i = lo + 1; i <= hi; i++) {
            if (compare(a[i], a[lo]) < 0) {
                swap(a, i, ++j);
            }
        }
        swap(a, lo, j);
        return j;
    }

    public String minNumberAnother(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            res.append(nums[i]);
        }
        return res.toString();
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int i = left, j = right;
        int pivot = nums[i];//基准值
        while (i < j) {
            while (i < j && comparator(pivot, nums[j])) {
                j--;
            }
            if (i < j) {
                nums[i++] = nums[j];
            }
            while (i < j && comparator(nums[i], pivot)) {
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        //循环结束，i == j
        nums[i] = pivot;
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    //a + b <= b + a，返回true
    private boolean comparator(int a, int b) {
        return compare(a, b) < 0;
    }
}
