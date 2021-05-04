package dayPlan;

import linklist.Link;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2021/3/6
 */
public class Day11 implements Link {
    //滑动窗口的最大值


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

    //138. 复制带随机指针的链表
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        // map方法，空间复杂度O(n)
        Node node = head;
        // 使用hash表存储旧结点和新结点的映射
        Map<Node, Node> map = new HashMap<>();
        //把node全部放到map里
        while (node != null) {
            Node clone = new Node(node.val);
            map.put(node, clone);
            node = node.next;
        }
        //node再重新指向head
        node = head;
        //再设置
        while (node != null) {
            //设置next和random
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        //
        return map.get(head);
    }

    //322. 零钱兑换
    int res = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        find(coins, coins.length - 1, amount, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void find(int[] coins, int len, int amount, int count) {
        //base case
        if (amount == 0) {
            res = Math.min(res, count);
            return;
        }
        //防止len越界,勿忘
        if (len < 0) return;
        for (int i = amount / coins[len]; i >= 0 && i + count < res; i--) {
            find(coins, len - 1, amount - i * coins[len], count + i);
        }
    }

    //动态规划:核心问题,我们该这么样利用subproblems
    public int coinChangeDp(int[] coins, int amount) {
        int max = amount + 1;
        //fill最大值
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        //0要处理下
        dp[0] = 0;
        //终于搞懂为啥要双层遍历了,第一层:处理每个金额
        for (int i = 1; i <= amount; i++) {
            //第二层:选择用哪个硬币的最优解
            for (int j = 0; j < coins.length; j++) {
                //如果硬币的值小于当前的索引,才计算,不然值就大了
                if (coins[j] <= i) {
                    //取最小,+1是加的当前金额的数量.
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        //大于说明还是amount+1没有处理过
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
