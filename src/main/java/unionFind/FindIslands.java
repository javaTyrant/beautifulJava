package unionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lumac
 * @since 2020/6/10
 */
public class FindIslands {
    class UnionFind {
        int[] parent;
        int[] rank;
        int count;

        UnionFind(int m, int n) {
            parent = new int[m * n];
            rank = new int[m * n];
        }

        int find(int i) {
            return parent[i] == i ? i : (parent[i] = find(parent[i]));
        }

        void union(int i, int j) {
            int first = find(i);
            int second = find(j);
            if (first == second) {
                return;
            }
            if (rank[first] > rank[second]) {
                parent[second] = first;
            } else if (rank[first] < rank[second]) {
                parent[first] = second;
            } else {
                parent[first] = second;
                rank[second]++;
            }
            count--;
        }

        int getCount() {
            return count;
        }
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        char[][] grid = new char[m][n];
        int[][] distance = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        UnionFind union = new UnionFind(m, n);
        List<Integer> res = new ArrayList<>();
        for (int[] pos : positions) {
            int i = pos[0];
            int j = pos[1];
            if (grid[i][j] == '1') {
                res.add(res.get(res.size() - 1));
                continue;
            }
            union.parent[i * n + j] = i * n + j;
            union.rank[i * n + j] = 1;
            union.count++;
            grid[i][j] = '1';
            int col = i * n + j;
            for (int[] dis : distance) {
                int x = i + dis[0];
                int y = j + dis[1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                    union.union(x * n + y, col);
                }
            }
            res.add(union.getCount());
        }
        return res;
    }

    public static void main(String[] args) {
        FindIslands findIslands = new FindIslands();
    }
    int ways, n, k;
    List<List<Integer>> edges;

    public int numWays(int n, int[][] relation, int k) {
        ways = 0;
        this.n = n;
        this.k = k;
        edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] edge : relation) {
            int src = edge[0], dst = edge[1];
            edges.get(src).add(dst);
        }
        dfs(0, 0);
        return ways;
    }

    public void dfs(int index, int steps) {
        if (steps == k) {
            if (index == n - 1) {
                ways++;
            }
            return;
        }
        List<Integer> list = edges.get(index);
        for (int nextIndex : list) {
            dfs(nextIndex, steps + 1);
        }
    }
}
