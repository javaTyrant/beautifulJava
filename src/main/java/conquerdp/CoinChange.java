package conquerdp;

import treenode.Tree;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/6/11
 **/
public class CoinChange implements Tree {
    //零钱兑换2:amount = 5, coins = [1, 2, 5]
    //用dp[x] 表示金额之和等于x的硬币组合数，目标是求dp[amount]。
    public static int change(int amount, int[] coins) {
        //dp的含义是?
        int[] dp = new int[amount + 1];
        //等于0的有一种方案.
        dp[0] = 1;
        //先选硬币
        for (int coin : coins) {
            //再判断.
            for (int i = 0; i + coin <= amount; i++) {
                //
                dp[i + coin] += dp[i];
            }
        }
        return dp[amount];
    }

    //完全平方数.
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        //初始化成最大值.
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            //
            for (int j = 1; j * j <= i; j++) {
                //
                if (i >= j * j) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
        }
        return dp[n];
    }


    //剑指 Offer 39. 数组中出现次数超过一半的数字
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            //
            if (count == 0) {
                //candidate赋值
                candidate = num;
            }
            //
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    //最小的k个数
    public int[] getLeastNumbers(int[] arr, int k) {
        //双指针
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int i = partition(arr, low, high);
            //等于k直接返回
            if (i == k) {
                return Arrays.copyOf(arr, k);
            }
            //-1
            if (i > k) high = i - 1;
            //+1
            if (i < k) low = i + 1;
        }
        return Arrays.copyOf(arr, k);
    }

    //快排的partition.
    int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= pivot)
                high--;
            nums[low] = nums[high];
            while (low < high && nums[low] <= pivot)
                low++;
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }

    //
    public static int countDigitOne(int n) {
        int digit = 1;
        int res = 0;
        int high = n / 10, curr = n % 10, low = 0;
        while (high != 0 || curr != 0) {
            if (curr == 0) {
                res += high * digit;
            } else if (curr == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }
            low += curr * digit;
            curr = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5};
        System.out.println(change(5, arr));
        System.out.println(numSquares(12));
        System.out.println(countDigitOne(12));
    }

    Map<Node, Node> map1 = new HashMap<>();//map前面保存原链表节点，后面保存复制节点，则可通过前面的key直接找到后面的value

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node res = new Node(head.val);
        map1.put(head, res);
        Node tmpR = res;
        Node tmpH = head;
        while (tmpH.next != null) {
            tmpR.next = new Node(tmpH.next.val);
            tmpR = tmpR.next;
            tmpH = tmpH.next;
            map1.put(tmpH, tmpR);
        }
        tmpR = res;
        tmpH = head;
        while (tmpH != null) {
            if (tmpH.random != null) tmpR.random = map1.get(tmpH.random);
            tmpH = tmpH.next;
            tmpR = tmpR.next;
        }
        return res;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public List<Integer> serialize(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode root) {
        if (root == null) {
            res.add(null);
        } else {
            res.add(root.val);
            dfs(res, root.left);
            dfs(res, root.right);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(List<Integer> data) {
        int index[] = {0};
        TreeNode root = build(index, data);
        return root;
    }

    private TreeNode build(int[] index, List<Integer> data) {
        Integer val = data.get(index[0]);
        index[0] = index[0] + 1;
        if (val == null) {
            return null;
        } else {
            TreeNode node = new TreeNode(val);
            node.left = build(index, data);
            node.right = build(index, data);
            return node;
        }
    }
}
