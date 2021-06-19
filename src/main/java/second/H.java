package second;

/**
 * @author lumac
 * @since 2021/6/14
 */
public class H {
    public static int hIndex(int[] citations) {
        int n = citations.length;
        int pivot, left = 0, right = n - 1;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (citations[pivot] == n - pivot) return n - pivot;
            else if (citations[pivot] < n - pivot) left = pivot + 1;
            else right = pivot - 1;
        }
        return n - left;
    }

    public static int hIndex2(int[] citations) {
        int n = citations.length;
        int lo = 0, hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo);
            //让我们思考一篇引用次数为 c 的文章，它的索引是 i，即 c = citations[i]。
            //我们可以知道，引用次数高于 c 的文章数量是 n-i-1。加上当前文章，有 n-i 个文章引用次数至少为 c 次。
            //所以:引用次数为c的文章
            if (citations[mid] < n - mid)
                lo = mid + 1;
            else
                hi = mid;
        }
        //
        return n - lo;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 5, 6};
        System.out.println(hIndex2(arr));
    }
}
