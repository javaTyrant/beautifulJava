package conquerdp;

import java.util.HashSet;
import java.util.Set;

/**
 * 数组中两个数的最大异或值
 *
 * @author lumac
 * @since 2021/5/16
 */
public class Xor2 {
    // 最高位的二进制位编号为 30
    static final int HIGH_BIT = 30;

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
                if (seen.contains(xNext ^ (num >> k))) {
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
}
