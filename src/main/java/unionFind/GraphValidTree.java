package unionFind;

/**
 * @author lumac
 * @since 2020/6/10
 */
public class GraphValidTree {
    /**
     * 1.是连通图
     * 2.不存在环
     */
    private class UnionFind {
        int[] p;

        public UnionFind(int size) {
            p = new int[size];
            for (int i = 0; i < size; i++) {
                p[i] = i;
            }
        }

        private int find(int x) {
            if (x != p[x]) {
                p[x] = find(p[x]);
            }
            return p[x];
        }

        public void union(int x, int y) {
            p[find(x)] = find(y);
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        UnionFind u = new UnionFind(n);
        for (int[] edge : edges) {
            u.union(edge[0], edge[1]);
        }

        for (int i = 0; i < n - 1; i++) {
            if (!u.isConnected(i, i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        GraphValidTree g = new GraphValidTree();
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println(g.validTree(5, edges));
    }
}
