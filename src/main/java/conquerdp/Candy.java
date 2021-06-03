package conquerdp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lufengxiang
 * @since 2021/6/1
 **/
public class Candy {
    //给你一个下标从0开始的正整数数组candiesCount，其中candiesCount[i]表示你拥有的第i类糖果的数目。同时给你一个二维数组queries，
    //其中queries[i] = [favoriteTypei, favoriteDayi, dailyCapi]。
    //你按照如下规则进行一场游戏：
    //你从第0天开始吃糖果。
    //你在吃完 所有第 i - 1类糖果之前，不能吃任何一颗第 i类糖果。
    //在吃完所有糖果之前，你必须每天 至少吃 一颗糖果。
    //请你构建一个布尔型数组answer，满足answer.length == queries.length 。answer[i]为true的条件是：
    //在每天吃不超过 dailyCapi颗糖果的前提下，你可以在第favoriteDayi天吃到第favoriteTypei类糖果；否则 answer[i]为 false。
    //注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
    //请你返回得到的数组answer。candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],
    //[2,13,1000000000]]
    //输出：[true,false,true]
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        //给定一个区间[a,b],使得
        int n = candiesCount.length;
        // 前缀和
        long[] sum = new long[n];
        sum[0] = candiesCount[0];
        for (int i = 1; i < n; ++i) {
            sum[i] = sum[i - 1] + candiesCount[i];
        }
        int q = queries.length;
        boolean[] ans = new boolean[q];
        for (int i = 0; i < q; ++i) {
            int[] query = queries[i];
            int favoriteType = query[0], favoriteDay = query[1], dailyCap = query[2];
            long x1 = favoriteDay + 1;
            long y1 = (long) (favoriteDay + 1) * dailyCap;
            long x2 = favoriteType == 0 ? 1 : sum[favoriteType - 1] + 1;
            long y2 = sum[favoriteType];
            ans[i] = !(x1 > y2 || y1 < x2);
        }
        return ans;
    }

    //nums = [23,2,4,6,7], k = 6 [2,4]
    //sum[j]−sum[i−1]=n∗k
    //sum[j]/k - sum[i-1]/k = n
    public static boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) return false;
        int[] sum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i < sum.length; i++) {
            //注意add的顺序.
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3};
        System.out.println(checkSubarraySum(arr, 6));
    }

    //560. 和为K的子数组
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int num : nums) {
            pre += num;
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
