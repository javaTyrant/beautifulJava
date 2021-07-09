package dayPlan;

import treenode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lufengxiang
 * @since 2021/6/29
 **/
public class BTreeDemo implements Tree {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 注意 1：一定要先把当前队列的结点总数暂存起来
            int currentSize = queue.size();
            //当前节点
            List<Integer> currentLevel = new ArrayList<>();
            //
            for (int i = 0; i < currentSize; i++) {
                //
                TreeNode front = queue.poll();
                //
                currentLevel.add(front.val);
                // 注意 2：左（右）孩子结点非空才加入队列
                if (front.left != null) {
                    queue.offer(front.left);
                }
                if (front.right != null) {
                    queue.offer(front.right);
                }
            }
            res.add(currentLevel);
        }
        return res;
    }

    //323. 无向图中连通分量的数目.
    public static int countComponents(int n, int[][] edges) {
        // 第 1 步：构建图
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        // 无向图，所以需要添加双向引用
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // 第 2 步：开始广度优先遍历
        int res = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(adj, i, visited);
                res++;
            }
        }
        return res;
    }

    /**
     * @param adj     邻接表
     * @param u       从 u 这个顶点开始广度优先遍历
     * @param visited 全局使用的 visited 布尔数组
     */
    private static void bfs(List<Integer>[] adj, int u, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);
        visited[u] = true;

        while (!queue.isEmpty()) {
            Integer front = queue.poll();
            // 获得队首结点的所有后继结点
            List<Integer> successors = adj[front];
            for (int successor : successors) {
                if (!visited[successor]) {
                    queue.offer(successor);
                    // 特别注意：在加入队列以后一定要将该结点标记为访问，否则会出现结果重复入队的情况
                    visited[successor] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println(countComponents(5, arr));
    }
}
