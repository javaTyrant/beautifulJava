package conquerdp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
