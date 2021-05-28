package conquerdp;

import java.util.HashSet;
import java.util.Set;

/**
 * 数组中两个数的最大异或值
 * 右移:
 * 左移:
 * 很棒的一题.
 *
 * @author lumac
 * @since 2021/5/16
 */
public class Xor2 {
    //O(N)的复杂度.
    public static void main(String[] args) {
        int[] arr = {3, 10, 5, 25, 2, 8};
        //0000 0011 3
        //0000 1010 10
        //0000 0101 5
        //0001 1001 25
        //0000 0010 2
        //0000 1000 8
        Xor2 solution = new Xor2();
        System.out.println(solution.findMaximumXOR2(arr));
        System.out.println(solution.findMaximumXOR(arr));
    }

    // 最高位的二进制位编号为 30
    static final int HIGH_BIT = 5;

    public int findMaximumXOR2(int[] nums) {
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; --k) {
            Set<Integer> seen = new HashSet<>();
            // 将所有的 pre^k(a_j) 放入哈希表中
            for (int num : nums) {
                // 如果只想保留从最高位开始到第 k 个二进制位为止的部分
                // 只需将其右移 k 位
                seen.add(num >> k);
            }
            // 目前 x 包含从最高位开始到第 k+1 个二进制位为止的部分
            // 我们将 x 的第 k 个二进制位置为 1，即为 x = x*2+1
            int xNext = x * 2 + 1;
            boolean found = false;

            // 枚举 i
            for (int num : nums) {
                //why? 因为 异或的交换律：a^b=c,a^c=b，b^c=a
                // 这里从结果倒推是否存在另一个数，它从最高位开始到第 k 个二进制位为止的部分，等于x_next ^ (num >> k)，
                // 交换律成立，k位上可以置一。 seen.count(x_next ^ (num >> k)
                if (seen.contains(xNext ^ (num >> k))) {
                    //true.
                    found = true;
                    break;
                }
            }

            if (found) {
                x = xNext;
            } else {
                // 如果没有找到满足等式的 a_i 和 a_j，那么 x 的第 k 个二进制位只能为 0
                // 即为 x = x*2
                x = xNext - 1;
            }
        }
        return x;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int findMaximumXOR(int[] nums) {
        TreeNode root = new TreeNode(-1);

        // build the tree
        for (int n : nums) {
            TreeNode node = root;
            for (int i = 31; i >= 0; i--) {
                if ((n & (1 << i)) == 0) { // 0
                    if (node.left == null) {
                        node.left = new TreeNode(0);
                    }
                    node = node.left;
                } else { // 1
                    if (node.right == null) {
                        node.right = new TreeNode(1);
                    }
                    node = node.right;
                }
            }
            node.left = new TreeNode(n);
        }

        int max = 0;
        for (int n : nums) {
            TreeNode node = root;
            for (int i = 31; i >= 0; i--) {
                if ((n & (1 << i)) == 0) {
                    if (node.right != null) {
                        node = node.right;
                    } else {
                        node = node.left;
                    }
                } else {
                    if (node.left != null) {
                        node = node.left;
                    } else {
                        node = node.right;
                    }
                }
            }
            int nn = node.left.val;
            max = Math.max(max, n ^ nn);
        }
        return max;
    }

    //找出所有子集的异或总和再求和
    public int subsetXORSum(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 30; i++) {
            int sum = 0;
            for (int x : nums) {
                sum += get(x, i);
            }
            if (sum > 0) {
                ans += (1 << i) << nums.length - 1;
            }
        }
        return ans;
    }

    public int get(int x, int i) {
        return (x >> i) & 1;
    }

    //5760. 构成交替字符串需要的最小交换次数
    public int minSwaps(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            cnt += c - '0';
        }
        int zero = s.length() - cnt;
        int inf = (int) 1e9;
        int ans = (int) 1e9;
        int n = s.length();
        if (cnt == zero || cnt == zero - 1) {
            ans = Math.min(ans, diff(s, create(0, n)));
        }
        if (cnt == zero || cnt == zero + 1) {
            ans = Math.min(ans, diff(s, create(1, n)));
        }
        return ans == inf ? -1 : ans;
    }

    public String create(int c, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(c);
            c ^= 1;
        }
        return sb.toString();
    }

    public Set<Integer> extract(String s) {
        Set<Integer> res = new HashSet();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                res.add(i);
            }
        }
        return res;
    }

    public int diff(String a, String b) {
        Set<Integer> la = extract(a);
        Set<Integer> lb = extract(b);
        int sum = 0;
        for (int x : la) {
            if (!lb.contains(x)) {
                sum += 1;
            }
        }
        return sum;
    }

    //5762. 恰有 K 根木棍可以看到的排列数目
    int mod = (int) 1e9 + 7;

    public int rearrangeSticks(int n, int k) {
        long[][] dp = new long[n][n + 1];
        dp[n - 1][1] = 1;
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] %= mod;
                if (dp[i][j] == 0) {
                    continue;
                }
                //add
                if (j + 1 <= n)
                    dp[i - 1][j + 1] += dp[i][j];
                //discard
                dp[i - 1][j] += dp[i][j] * (n - i) % mod;
            }
        }
        return (int) (dp[0][k] % mod);
    }
}
