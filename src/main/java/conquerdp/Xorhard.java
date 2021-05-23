package conquerdp;

import java.util.*;

/**
 * @author lumac
 * @since 2021/5/23
 */
public class Xorhard {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4};
        int[][] queries = {{3, 1}, {1, 3}, {5, 6}};
        Xorhard solution = new Xorhard();
        solution.maximizeXor(nums, queries);
        String s = "011010";
        solution.canReach2(s, 2, 3);
        int[] arr = {2, 1, 5, 0, 4, 6};
        solution.increasingTriplet(arr);
    }

    //300. 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        //以i结尾的最长的数量
        int[] dp = new int[nums.length];
        dp[0] = 1;
        //至少有一个
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //如果i > j的值
                if (nums[i] > nums[j]) {
                    //i处和前一个+当前的最大值.
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //选最大的.
            res = Math.max(dp[i], res);
        }
        return res;
    }

    //
    public boolean increasingTriplet(int[] nums) {
        int big = Integer.MAX_VALUE, small = Integer.MAX_VALUE;
        for (int i : nums) {
            // 通过if的结构保证递增！
            if (i <= small) small = i;
                // 走到这一步说明这个值大于前面的值(i>small)
            else if (i <= big) big = i;
                // 走到这一步说明这个值大于前面的两个值（i>big>small）
            else return true;
        }
        return false;
    }

    public static boolean increasingTripletDp(int[] nums) {
        int m = nums.length;
        int[] dp = new int[m];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < m; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        //干!!!
        return max >= 3;
    }

    //nlogn解法.
    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }


    //1553. 吃掉 N 个橘子的最少天数
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int minDays(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        memo.put(n, Math.min(n % 2 + 1 + minDays(n / 2), n % 3 + 1 + minDays(n / 3)));
        return memo.get(n);
    }


    public boolean canReach(String s, int minJump, int maxJump) {
        if (s.charAt(s.length() - 1) != '0') return false;
        Queue<Integer> que = new LinkedList<>();//存index
        que.offer(0);
        int last = 0; //尾指针
        int len = s.length();

        while (!que.isEmpty()) {
            int index = que.poll();
            for (int i = Math.max(last + 1, index + minJump); i <= Math.min(index + maxJump, len - 1); i++) {
                last = i;
                if (s.charAt(i) != '0') continue;
                que.offer(i);
                if (i == len - 1) return true;
            }
        }
        return false;
    }

    public boolean canReach2(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] S = new int[n + 1];  //这是dp的前缀和。卧槽
        boolean[] dp = new boolean[n + 1]; // 代表 是否存在一种路径跳到 i.

        if (s.charAt(s.length() - 1) == '1') return false;
        if (s.length() == 1) return true;
        if (minJump > s.length()) return false;
        dp[1] = true;
        S[1] = 1; //有方案。
        for (int i = 2; i <= n; i++) {
            //如果当前位置的前一个是0且
            if (s.charAt(i - 1) == '0' && i - minJump >= 1) {
                //
                int l = Math.max(1, i - maxJump);
                int r = i - minJump;
                //如果s[r]大于s[l-1]什么意思呢.
                if (S[r] > S[l - 1]) {
                    dp[i] = true;
                }
            }
            //
            S[i] = S[i - 1] + (dp[i] ? 1 : 0);
        }
        return dp[n];
    }

    //前缀树解法
    public int[] maximizeXor(int[] nums, int[][] queries) {
        //对数组排序
        Arrays.sort(nums);
        //查询数组的长度
        int numQ = queries.length;
        //新的二维数组
        int[][] newQueries = new int[numQ][3];
        for (int i = 0; i < numQ; ++i) {
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i;
        }
        //
        Arrays.sort(newQueries, Comparator.comparingInt(query -> query[1]));
        //
        int[] ans = new int[numQ];
        //前缀树
        Trie trie = new Trie();
        //
        int idx = 0, n = nums.length;
        //
        for (int[] query : newQueries) {
            int x = query[0], m = query[1], qid = query[2];
            while (idx < n && nums[idx] <= m) {
                trie.insert(nums[idx]);
                ++idx;
            }
            if (idx == 0) { // 字典树为空
                ans[qid] = -1;
            } else {
                ans[qid] = trie.getMaxXor(x);
            }
        }
        return ans;
    }
}

class Trie {
    static final int L = 30;
    Trie[] children = new Trie[2];

    public void insert(int val) {
        Trie node = this;
        for (int i = L - 1; i >= 0; --i) {
            //
            int bit = (val >> i) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new Trie();
            }
            node = node.children[bit];
        }
    }

    public int getMaxXor(int val) {
        int ans = 0;
        Trie node = this;
        for (int i = L - 1; i >= 0; --i) {
            int bit = (val >> i) & 1;
            if (node.children[bit ^ 1] != null) {
                ans |= 1 << i;
                bit ^= 1;
            }
            node = node.children[bit];
        }
        return ans;
    }


}
