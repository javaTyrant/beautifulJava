import conquerdp.offer.P;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lufengxiang
 * @since 2021/7/5
 **/
public class LongestCommonPath {
    class Solution {
        long P = 131L;
        long[] h1;
        long[] h2;
        long[] p;
        int n;
        int m;

        public int findLength(int[] nums1, int[] nums2) {
            //假设 最长重复子数组 的长度为mid ,则枚举nums1中的所有mid长度的子数组hash ,枚举nums2中的所有mid长度的子数组hash.如果存在数组的hash值相同，则说明mid即为解，否则二分求mid
            n = nums1.length;
            m = nums2.length;
            h1 = new long[n + 1];
            h2 = new long[m + 1];
            for (int i = 1; i <= n; i++) h1[i] = h1[i - 1] * P + nums1[i - 1];
            for (int i = 1; i <= m; i++) h2[i] = h2[i - 1] * P + nums2[i - 1];
            p = new long[n + 1];
            p[0] = 1;
            for (int i = 1; i <= n; i++) p[i] = p[i - 1] * P;
            int l = 0, r = n;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                //System.out.println(" ord mid: " + mid);
                if (check(mid)) l = mid;

                else
                    r = mid - 1;
            }
            return r;
        }

        boolean check(int mid) {
            Set<Long> set = new HashSet<>();
            for (int i = mid; i <= n; i++) {
                set.add(get(h1, i, i - mid + 1));
            }
            for (int i = mid; i <= m; i++) {
                if (set.contains(get(h2, i, i - mid + 1))) {
                    return true;
                }
            }
            return false;
        }

        long get(long[] h, int r, int l) {
            return h[r] - p[r - l + 1] * h[l - 1];
        }
    }

    //718. 最长重复子数组:给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
    public int findLength(int[] A, int[] B) {
        if (A == null || B == null) {
            return 0;
        }
        int res = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    //时间复杂度：O((M+N)log(min(M,N)))。
    //空间复杂度：O(N)。
    int mod = 1000000009;
    int base = 113;

    public int findLengthHash(int[] A, int[] B) {
        //
        int left = 1, right = Math.min(A.length, B.length) + 1;
        //
        while (left < right) {
            int mid = (left + right) >> 1;
            //
            if (check(A, B, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    //序列S的哈希值如何计算.
    public boolean check(int[] A, int[] B, int len) {
        //hashA是什么意思呢?
        long hashA = 0;
        //而为了优化时间复杂度
        for (int i = 0; i < len; i++) {
            //不断的更新hashA
            hashA = (hashA * base + A[i]) % mod;
        }
        //保存hashA
        Set<Long> bucketA = new HashSet<>();
        //
        bucketA.add(hashA);
        //
        long mult = qPow(base, len - 1);
        //为什么这么计算呢?
        for (int i = len; i < A.length; i++) {
            //
            hashA = ((hashA - A[i - len] * mult % mod + mod) % mod * base + A[i]) % mod;
            bucketA.add(hashA);
        }
        //为什么hashB和hashA的计算不一样呢?
        long hashB = 0;
        for (int i = 0; i < len; i++) {
            hashB = (hashB * base + B[i]) % mod;
        }
        if (bucketA.contains(hashB)) {
            return true;
        }
        //
        for (int i = len; i < B.length; i++) {
            hashB = ((hashB - B[i - len] * mult % mod + mod) % mod * base + B[i]) % mod;
            if (bucketA.contains(hashB)) {
                return true;
            }
        }
        return false;
    }

    // 使用快速幂计算 x^n % mod 的值
    public long qPow(long x, long n) {
        long ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                ret = ret * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {0, 1, 2, 3, 4},
                {2, 3, 4},
                {4, 0, 1, 2, 3}
        };
        LongestCommonPath path = new LongestCommonPath();
        System.out.println(path.longestCommonSubpath(5, arr));
        int[] a = {2, 3, 4, 3, 3};
        int[] b = {0, 1, 2, 3, 4};
        System.out.println(path.findLengthHash(a, b));
    }

    int N = 100010;
    long[] h = new long[N];
    long[] p = new long[N];
    int[][] path;
    Map<Long, Integer> cnt = new HashMap<>();
    Map<Long, Integer> inner = new HashMap<>();

    public int longestCommonSubpath(int n, int[][] paths) {
        this.path = paths;
        int l = 0;
        int r = N;
        for (int[] ints : paths) {
            r = Math.min(r, ints.length);
        }
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid))
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }

    private boolean check(int mid) {
        cnt.clear();
        inner.clear();
        p[0] = 1;
        for (int j = 0; j < path.length; j++) {
            int n = path[j].length;
            for (int i = 1; i <= n; i++) {
                p[i] = p[i - 1] * 133331;
                h[i] = h[i - 1] * 133331 + path[j][i - 1];
            }
            for (int i = mid; i <= n; i++) {
                long val = get(i - mid + 1, i);
                if (!inner.containsKey(val) || inner.get(val) != j) {
                    inner.put(val, j);
                    cnt.put(val, cnt.getOrDefault(val, 0) + 1);
                }
            }
        }
        int max = 0;
        for (long key : cnt.keySet()) {
            max = Math.max(max, cnt.get(key));
        }
        return max == path.length;
    }

    public long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
