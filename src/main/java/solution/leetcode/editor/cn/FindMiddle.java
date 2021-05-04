package solution.leetcode.editor.cn;

/**
 * @author lumac
 * @since 2020/12/26
 */
public class FindMiddle {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 11, 17};
        int[] arr1 = {9, 10, 11, 13, 14};
        findMedianSortedArraysPart(arr, arr1);
    }

    //划分数组法
    public static double findMedianSortedArraysPart(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        //取保n是较大的那一个
        if (m > n) {
            return findMedianSortedArraysPart(B, A);
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            //
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            //我们要找到b[j-1]<= A[i] 且A[i-1]<B[j],边界判断不能忘了哦
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small 二分法经典模板吗
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else {
                int maxLeft;
                //边界值判断,i==0说明A用完了
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {//说明B用完了
                    maxLeft = A[i - 1];
                } else {
                    //否则取左边最大的
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                //奇数
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                //右边最小值,跟左边最大值是相同的逻辑
                int minRight;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
