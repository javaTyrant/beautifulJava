package sort;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/6/29
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 7, 3, 6, 1, 4};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //字符串相乘
    public String multiply(String num1, String num2) {
        int sumLen = num1.length() + num2.length();
        //用数组
        int[] res = new int[sumLen];
        for (int i = 0; i < num1.length(); i++) {
            int num11 = num1.charAt(num1.length() - 1 - i) - '0';//3
            for (int j = 0; j < num2.length(); j++) {
                int num22 = num2.charAt(num2.length() - 1 - j) - '0';//6,5,4
                res[i + j] += num11 * num22;            //序列和相同相加
            }
        }
        for (int i = 0; i < res.length - 1; i++) {
            if (res[i] >= 10) {
                res[i + 1] += res[i] / 10;//后位加上
                res[i] %= 10;//余数
            }
        }
        int i = res.length - 1;
        while (i > 0 && res[i] == 0) {
            i--;
        } // 去除结果前面的 0
        StringBuilder sb = new StringBuilder();
        for (; i >= 0; i--) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    //：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。
    // 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
    public static void sort(int[] pq) {
        int n = pq.length;
        //能保证k一定是根节点吗
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }
        int k = n;
        //k肯定是大于0的为什么=1可以不用呢?交换的是00,重复了,没有必要
        while (k >= 1) {
            //
            exch(pq, 1, k--);
            //
            sink(pq, 1, k);
        }
    }

    //下沉操作
    private static void sink(int[] pq, int k, int n) {
        //取保不越界,
        while (2 * k <= n) {
            int j = 2 * k;
            //先比较两个子节点,选一个大的
            if (j < n && less(pq, j, j + 1)) j++;
            //如果k大于j就不用交换了
            if (!less(pq, k, j)) break;
            //arr[k] >= arr[j],交换下
            exch(pq, k, j);
            //k = j
            k = j;
        }
    }

    @SuppressWarnings("unchecked")
    static boolean less(int[] pq, int i, int j) {
        //都减一
        return pq[i - 1] < pq[j - 1];
    }

    static void exch(int[] pq, int i, int j) {
        //都是减一,等于索引0没有放位置
        int swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

}
