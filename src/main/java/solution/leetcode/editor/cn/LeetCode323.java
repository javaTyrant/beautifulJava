package solution.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 无向图中连通分量的数目
 *
 * @author lumac
 * @since 2020/12/20
 */
public class LeetCode323 {
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        LeetCode323 solution = new LeetCode323();
        System.out.println(solution.countComponents(5, edges));
    }

    //并查集
    public int countComponents(int n, int[][] edges) {
        UnionFind u = new UnionFind(n);
        for (int[] edge : edges) {
            u.union(edge[0], edge[1]);
        }
        return u.size();
    }

    private class UnionFind {
        //
        int[] p;

        //初始化
        public UnionFind(int size) {
            p = new int[size];
            for (int i = 0; i < size; i++) {
                p[i] = i;
            }
        }

        //何为?找到某个元素的根节点
        private int find(int x) {
            //如果x不能与自己
            if (x != p[x]) {
                //递归查找
                p[x] = find(p[x]);
            }
            return p[x];
        }

        //何为?合并两个元素为同一个根节点
        public void union(int v, int w) {
            p[find(v)] = find(w);
        }

        //何为?查找不相交的数量,也就是找到不同的根节点的数量
        public int size() {
            //用一个set保存不同根节点的数量
            Set<Integer> set = new HashSet<>();
            for (int x : p) {
                set.add(find(x));
            }
            return set.size();
        }
    }
}
