package solution.leetcode.editor.cn;

//两个有序数组的中位数
public class LeetCode4 {
    //leetcode submit region begin(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solution = new Solution();
        //int[] arr = {1, 3, 5, 11, 17};
        //int[] arr1 = {9, 10, 11, 13, 14};
        //System.out.println(solution.findMedianSortedArrays(arr, arr1));
        int[] arr2 = {1, 2, 3, 4};
        int[] arr3 = {5, 6, 7, 8, 9};
        System.out.println(solution.findMedianSortedArraysBi(arr2, arr3));

        //System.out.println(solution.findMedianSortedArraysBi(arr, arr1));
        //System.out.println(solution.findMedianSortedArraysPart(arr, arr1));
    }

    static class SolutionRe {
        public static void main(String[] args) {
            int[] arr2 = {1, 3};
            int[] arr3 = {2};
            System.out.println(findMedianSortedArrays(arr2, arr3));
        }

        //分治法
        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            //左右各找中间
            int left = (m + n + 1) / 2;
            int right = (m + n + 2) / 2;
            return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
        }

        /**
         * @param nums1 数组1
         * @param i     索引1
         * @param nums2 数组2
         * @param j     索引2
         * @param k     第几小的数
         * @return
         */
        public static int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
            //base case nums1用完了,取nums2
            if (i >= nums1.length) return nums2[j + k - 1];//nums1为空数组
            if (j >= nums2.length) return nums1[i + k - 1];//nums2为空数组
            //k == 1什么时候k==1呢
            if (k == 1) {
                return Math.min(nums1[i], nums2[j]);
            }
            //先边界判断,然后移动k/2个 如果不减一的话,[1,2] [3,4]
            int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
            int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
            //更新k的值
            if (midVal1 < midVal2) {
                //移动既舍弃,k要减去舍弃的值
                return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
            } else {
                return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
            }
        }
    }

    //自己用二分法去写
    static class MySolution {
        public static void main(String[] args) {
            int[] arr2 = {2};
            int[] arr3 = {1, 3, 4};
            System.out.println(findMedianSortedArrays(arr2, arr3));
        }

        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int m = nums2.length;
            return (findKth(nums1, nums2, 0, 0, (m + n + 1) / 2) + findKth(nums1, nums2, 0, 0, (n + m + 2) / 2)) / 2.0;
        }

        private static double findKth(int[] nums1, int[] nums2, int i, int j, int k) {
            int n = nums1.length;
            int m = nums2.length;
            //下面两个分支肯定是一个数组已经用完了
            //也就是求一个数组的第k小值
            if (n == i) {
                //j+k-1这个是什么意思
                //return nums2[0];
                return nums2[j + k - 1];
            }
            if (m == j) {
                //return nums1[0];
                return nums1[i + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[i], nums2[j]);
            }
            //为什么要k/2 - 1如果是k/2并不能保证舍弃哪部分,只有k/2-1能保证舍去最大的值
            int mid1 = (i + k / 2 - 1) < n ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
            int mid2 = (j + k / 2 - 1) < m ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
            if (mid1 < mid2) {
                //这边不要减1了
                return findKth(nums1, nums2, i + k / 2, j, k - k / 2);
            } else {
                return findKth(nums1, nums2, i, j + k / 2, k - k / 2);
            }
        }
    }

    static class Solution {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */


        public int getKthElement(int[] nums1, int[] nums2, int k) {
            int length1 = nums1.length, length2 = nums2.length;
            int index1 = 0, index2 = 0;

            while (true) {
                // 边界情况
                if (index1 == length1) {
                    return nums2[index2 + k - 1];
                }
                if (index2 == length2) {
                    return nums1[index1 + k - 1];
                }
                //k什么时候==1
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                //为什么k要除2
                int half = k / 2;
                //防止index + half越界,为什么要移动half呢
                int newIndex1 = Math.min(index1 + half, length1) - 1;
                System.out.println("newIndex1:" + newIndex1);
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    //放弃pivot1左边的数据
                    k -= (newIndex1 - index1 + 1);
                    //更新index的值+1
                    index1 = newIndex1 + 1;
                } else {
                    //放弃pivot2左边的数据
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }
        }

        //划分数组法
        public double findMedianSortedArraysPart(int[] A, int[] B) {
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
                if (i < iMax && B[j - 1] > A[i]) {
                    iMin = i + 1; // i is too small
                } else if (i > iMin && A[i - 1] > B[j]) {
                    iMax = i - 1; // i is too big
                } else { // i is perfect
                    int maxLeft;
                    if (i == 0) {
                        maxLeft = B[j - 1];
                    } else if (j == 0) {
                        maxLeft = A[i - 1];
                    } else {
                        maxLeft = Math.max(A[i - 1], B[j - 1]);
                    }
                    if ((m + n) % 2 == 1) {
                        return maxLeft;
                    }
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

        public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays2(nums2, nums1);
            }

            int m = nums1.length;
            int n = nums2.length;
            int left = 0, right = m;
            // median1：前一部分的最大值
            // median2：后一部分的最小值
            int median1 = 0, median2 = 0;

            while (left <= right) {
                // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
                // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
                int i = (left + right) / 2;
                int j = (m + n + 1) / 2 - i;
                // numsIm1, numsI, numsJm1, numsJ 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
                int numsIm1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
                int numsI = (i == m ? Integer.MAX_VALUE : nums1[i]);
                int numsJm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
                int numsJ = (j == n ? Integer.MAX_VALUE : nums2[j]);

                if (numsIm1 <= numsJ) {
                    median1 = Math.max(numsIm1, numsJm1);
                    median2 = Math.min(numsI, numsJ);
                    left = i + 1;
                } else {
                    right = i - 1;
                }
            }
            return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
        }

        //时间复杂度O(log(m+n))核心思想:两个数组中找第K小的数字,我们该放弃什么
        public double findMedianSortedArraysBi(int[] nums1, int[] nums2) {
            //先保存两个数组的大小
            int n = nums1.length, m = nums2.length;
            //总长度
            //奇数,肯定在一个数组中
            return (getKthElement(nums1, nums2, (m + n + 1) / 2) + getKthElement(nums1, nums2, (m + n + 2) / 2)) / 2.0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
