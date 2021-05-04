package solution.leetcode.editor.cn;

import java.util.*;

/**
 * @author lumac
 * @since 2021/1/3
 */
public class FirstWeek {
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);
        int res = 0;
        for (int[] box : boxTypes) {
            int left = Math.min(box[0], truckSize);
            left = left < 0 ? 0 : left;
            res += box[1] * left;
            truckSize -= box[0];
        }
        return res;
    }

    //怎么优化呢?
    public static int countPairs(int[] deliciousness) {
        int res = 0;
        for (int i = 0; i < deliciousness.length; i++) {
            int j = i + 1;
            while (j < deliciousness.length) {
                int n = deliciousness[i] + deliciousness[j];
                if (n > 0 && (n & n - 1) == 0) {
                    res++;
                }
                j++;
            }
        }
        return res;
    }

    //如何优化呢
    public static int countPairsOpt(int[] deliciousness) {
        int[] cnts = new int[(1 << 21) + 1];
        for (int x : deliciousness) {
            cnts[x]++;
        }
        long ans = 0;
        for (int x : deliciousness) {
            for (int j = 1; j <= 1 << 21; j <<= 1) {
                int other = j - x;
                if (other < 0) {
                    continue;
                }
                ans += cnts[other];
            }
            if (Integer.bitCount(x + x) == 1) {
                ans--;
            }
        }
        ans /= 2;
        int mod = (int) 1e9 + 7;
        return (int) (ans % mod);
    }

    int[] ps;

    public int sum(int l, int r) {
        if (l > r) {
            return 0;
        }
        int ans = ps[r];
        if (l > 0) {
            ans -= ps[l - 1];
        }
        return ans;
    }

    //将数组分成三个子数组的方案数
    public int waysToSplit(int[] nums) {
        int r = 0;
        int l = 0;
        ps = nums.clone();
        for (int i = 1; i < ps.length; i++) {
            ps[i] += ps[i - 1];
        }

        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            l = Math.max(l, i + 1);
            //r = Math.max(r, i + 1);
            while (l < n && sum(0, i) > sum(i + 1, l)) {
                l++;
            }
            while (r + 2 < n && sum(i + 1, r + 1) <= sum(r + 2, n - 1)) {
                r++;
            }
            if (l <= r) {
                ans += r - l + 1;
            }
            ///System.out.println(i + "," + l + "," + r);
        }
        int mod = (int) 1e9 + 7;
        return (int) (ans % mod);
    }

    public static void main(String[] args) {
        int[] p1 = {149, 107, 63, 0, 1, 1, 6867, 1325, 5611, 2581, 39, 89, 46, 18, 12, 20, 22, 234};
        System.out.println(countPairs(p1));
        System.out.println(countPairsOpt(p1));

    }
}
