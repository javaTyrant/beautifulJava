package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;

public class LeetCode862 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //超时,怎么优化呢
        public static int shortestSubarray_(int[] array, int s) {
            //哪些计算是重复的呢
            Integer min = Integer.MAX_VALUE;
            for (int i = 0; i < array.length; i++) {
                int count = 0;
                int sum = 0;
                sum += array[i];
                if (sum >= s) return 1;
                int j = i + 1;
                if (j == array.length) break;
                int k = array.length;
                while (j < k) {
                    count++;
                    sum += array[j];
                    j++;
                    if (sum >= s && count < min) {
                        min = count;
                        break;
                    }
                }
            }
            return min == Integer.MAX_VALUE ? -1 : min + 1;
        }

        //和至少为 K 的最短子数组
        public static int shortestSubarray(int[] array, int k) {
            int n = array.length;
            //前缀和
            int[] sum = new int[n + 1];
            //
            int[] que = new int[n + 2];
            //i到j的元之和是sum[j+1]-sum[i]
            for (int i = 0; i < n; i++)
                sum[i + 1] = sum[i] + array[i];
            System.out.println(Arrays.toString(sum));
            int left = 0, right = -1, index = 0, ans = n + 1;
            while (index <= n) {
                while (right - left >= 0 && sum[index] <= sum[que[right]])
                    right--;
                while (right - left >= 0 && sum[index] - sum[que[left]] >= k) {
                    ans = Math.min(ans, index - que[left]);
                    left++;
                }
                //保存索引,什么时候保存索引呢
                //right - left < 0 或者sum[index] > sum[que[right]]
                //sum[index] - sum[que[left]] < k
                que[++right] = index++;
            }
            if (ans == n + 1) return -1;
            return ans;
        }

        //和为K的所有数组
        public static List<Integer> findAllSum(int[] arr, int sum) {
            return null;
        }

        public static void main(String[] args) {
            int[] arr = {56, -21, 56, 35, -9};
            int[] arr1 = {2, -2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4};
            System.out.println(shortestSubarray(arr1, 4));
            System.out.println(shortestSubarray_(arr1, 4));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
