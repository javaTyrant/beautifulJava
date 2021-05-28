package conquerdp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lufengxiang
 * @since 2021/5/25
 **/
public class Russia {
    public static void main(String[] args) {
        int[][] arr = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        Russia solution = new Russia();
        System.out.println(solution.maxEnvelopes(arr));
    }

    //当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
    //俄罗斯套娃信封问题[5,4],[6,4],[6,7],[2,3]
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public int maxEnvelopes(int[][] envelopes) {
        //排序:如果第一个相等,递减
        //否则第二个递增
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) {
                return arr2[1] - arr1[1];
            } else {
                return arr1[0] - arr2[0];
            }
        });
        // extract the second dimension and run LIS
        int[] secondDim = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; ++i) {
            secondDim[i] = envelopes[i][1];
        }
        return lengthOfLIS(secondDim);
    }
}
