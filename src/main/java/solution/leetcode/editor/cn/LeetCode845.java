package solution.leetcode.editor.cn;

public class LeetCode845 {
    static class MySolution {
        //思路简单但是细节很多啊
        public static int longestMountain(int[] arr) {
            if (arr == null || arr.length < 3) return 0;
            int max = 0;
            for (int i = 1; i < arr.length - 1; i++) {
                //防止重复情况
                if (arr[i - 1] < arr[i] && arr[i + 1] < arr[i]) {
                    //往左右扫描
                    int left = i - 1;
                    int right = i + 1;
                    while (left > 0 && arr[left - 1] < arr[left]) {
                        left--;
                    }
                    while (right < arr.length - 1 && arr[right + 1] < arr[right]) {
                        right++;
                    }
                    max = Math.max(max, right - left + 1);
                }
            }
            return max;
        }

        public static void main(String[] args) {
            int[] arr = {2, 1, 4, 7, 3, 2, 5};
            int[] arr1 = {2, 2, 2};
            int[] arr2 = {0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0};
            System.out.println(longestMountain(arr));
            System.out.println(longestMountain(arr1));
            System.out.println(longestMountain(arr2));
        }


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //输入：[2,1,4,7,3,2,5]
        //解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
        public int longestMountain(int[] A) {
            if (A == null || A.length <= 2) {
                return 0;
            }
            int res = 0;
            //从1开始
            for (int i = 1; i < A.length - 1; i++) {
                if (A[i - 1] < A[i] && A[i + 1] < A[i]) {
                    //双指针
                    int l = i - 1;
                    int r = i + 1;
                    while (l > 0 && A[l - 1] < A[l]) {
                        l--;
                    }
                    while (r < A.length - 1 && A[r + 1] < A[r]) {
                        r++;
                    }
                    res = Math.max(res, (r - l + 1));
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
