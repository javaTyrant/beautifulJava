package array;

/**
 * @author lumac
 * @since 2020/5/31
 */
public class FindMiddleFromTwoArray {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        System.out.println(findMedianSortedArrays(arr1, arr2));
        System.out.println(findMedianSortedArrays_(arr1, arr2));
    }

    //时间复杂度O(log(min(m,n)))
    public static double findMedianSortedArrays_(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        // to ensure m<=n
        //确保n>=m
        if (m > n) {
            return findMedianSortedArrays_(B, A);
        }
        //不加一会死循环
        //两个指针
        int iMin = 0;
        int iMax = m;
        //+1确保奇偶都没有问题
        int halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            //i,j两个数组的划分点
            //二分法取中间
            int i = (iMin + iMax) / 2;
            //确保数组的划分
            int j = halfLen - i;
            //j不能等于0
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1;
                // i is too big
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1;
            } else {
                //i刚刚好
                //记录两个值,左边的最大值,右边的最小值
                int maxLeft;
                //左边的极端值处理
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                //奇数
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                int minRight;
                //右边的极端值处理
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

    //优化版本,统一处奇数偶数
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //骚
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int low = 0, high = m * 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            //
            int c = m + n - mid;
            //如果把奇偶数统一处理
            int L1 = mid == 0 ? Integer.MIN_VALUE : nums1[(mid - 1) / 2];
            int R1 = mid == 2 * m ? Integer.MAX_VALUE : nums1[mid / 2];
            int L2 = c == 0 ? Integer.MIN_VALUE : nums2[(c - 1) / 2];
            int R2 = c == 2 * n ? Integer.MAX_VALUE : nums2[c / 2];
            if (L1 > R2) {
                high = mid - 1;
            } else if (L2 > R1) {
                low = mid + 1;
            } else {
                return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
            }
        }
        return -1;
    }

    /**
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArraysAnother(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    //i: nums1的起始位置 j: nums2的起始位置
    public int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) return nums2[j + k - 1];//nums1为空数组
        if (j >= nums2.length) return nums1[i + k - 1];//nums2为空数组
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }

}
