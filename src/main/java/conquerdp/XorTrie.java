package conquerdp;

import bit.Xor;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lufengxiang
 * @since 2021/5/24
 **/
public class XorTrie {
    public static void main(String[] args) {
        XorTrie solution = new XorTrie();
        int[] nums = {0, 1, 2, 3, 4};
        int[][] queries = {{3, 1}, {1, 3}, {5, 6}};
        solution.maximizeXor(nums, queries);
        Trie trie = new Trie();
        trie.insert(1);
        trie.insert(2);
        System.out.println(trie.getMaxXor(4));
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

    //二进制的前缀树
    static class Trie {
        //30位
        static final int L = 30;
        //只有两个节点.
        Trie[] children = new Trie[2];

        public void insert(int val) {
            Trie node = this;
            for (int i = L - 1; i >= 0; --i) {
                //右移以为判断是1还是0
                int bit = (val >> i) & 1;
                //空则新增
                if (node.children[bit] == null) {
                    node.children[bit] = new Trie();
                }
                //移动node.
                node = node.children[bit];
            }
        }

        //获取最大的Xor
        public int getMaxXor(int val) {
            int ans = 0;
            Trie node = this;
            for (int i = L - 1; i >= 0; --i) {
                int bit = (val >> i) & 1;
                if (node.children[bit ^ 1] != null) {
                    //
                    ans |= 1 << i;
                    //
                    bit ^= 1;
                }
                //
                node = node.children[bit];
            }
            return ans;
        }
    }
}
