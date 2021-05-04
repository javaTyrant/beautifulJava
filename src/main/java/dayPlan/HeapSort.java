package dayPlan;

/**
 * @author lumac
 * @since 2021/3/4
 */
public class HeapSort {
    //：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。
    // 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
    public static void sort(int[] pq) {
        //先获取数组的长度
        int n = pq.length;
        //循环sink.sink之后就是大顶堆了
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }
        //赋值给k
        int k = n;
        //k肯定是大于0的为什么=1可以不用呢?交换的是00,重复了,没有必要
        while (k >= 1) {
            //交换 k--,从1开始
            exch(pq, 1, k--);
            //
            sink(pq, 1, k);
        }
    }

    //下沉操作
    private static void sink(int[] pq, int k, int n) {
        //取保不越界,
        while (2 * k <= n) {
            //第一个子节点
            int j = 2 * k;
            //先比较两个子节点,选一个大的
            if (j < n && less(pq, j, j + 1)) j++;
            //如果k大于j就不用交换了
            if (!less(pq, k, j)) break;
            //arr[k] >= arr[j],交换下
            exch(pq, k, j);
            //把j赋值给k
            k = j;
        }
    }

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
