package solution.leetcode.editor.cn;

import java.util.*;

public class LeetCode399 {
    static class GraphSolution {
        public static void main(String[] args) {
            GraphSolution solution = new GraphSolution();
            List<List<String>> es = new ArrayList<>();
            es.add(Arrays.asList("a", "b"));
            es.add(Arrays.asList("b", "c"));
            es.add(Arrays.asList("c", "d"));
            List<List<String>> qs = new ArrayList<>();
            qs.add(Arrays.asList("a", "c"));
            double[] vs = {1.5, 2.5, 2};
            System.out.println(Arrays.toString(solution.calcEquation(es, vs, qs)));
        }

        HashMap<String, List<Edge>> mEdges = new HashMap<>();
        double[] mRes;

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // 1. 构建加权有向图
            for (int i = 0; i < equations.size(); i++) {
                List<String> edge = equations.get(i);
                String v1 = edge.get(0);
                String v2 = edge.get(1);
                double val = values[i];
                // 添加到 v1->v2 的边
                List<Edge> v1Edges = mEdges.get(v1);
                //
                if (v1Edges == null) {
                    v1Edges = new ArrayList<>();
                    mEdges.put(v1, v1Edges);
                }
                v1Edges.add(new Edge(v1, v2, val));
                // 添加到 v2->v1 的边
                List<Edge> v2Edges = mEdges.get(v2);
                if (v2Edges == null) {
                    v2Edges = new ArrayList<>();
                    mEdges.put(v2, v2Edges);
                }
                v2Edges.add(new Edge(v2, v1, 1.0 / val));
            }

            // 2. dfs 搜索数据
            mRes = new double[queries.size()];
            List<String> visited = new ArrayList<>();
            for (int i = 0; i < queries.size(); i++) {
                List<String> query = queries.get(i);
                String start = query.get(0);
                String dest = query.get(1);
                visited.clear();
                mRes[i] = dfs(start, dest, visited);
            }
            return mRes;
        }

        private double dfs(String start, String dest, List<String> visited) {
            // 验证是否存顶点
            if (!mEdges.containsKey(start) || !mEdges.containsKey(dest)) {
                return -1.0;
            }
            visited.add(start);
            if (start.equals(dest)) {
                return 1.0;
            }
            // 获取 start 顶点的边
            List<Edge> startEdges = mEdges.get(start);
            if (startEdges == null || startEdges.isEmpty()) {
                return -1.0;
            }
            // 深度优先遍历集合
            for (Edge edge : startEdges) {
                //如果被访问过了,继续
                if (visited.contains(edge.to)) {
                    continue;
                }
                //否则继续dfs
                double res = dfs(edge.to, dest, visited);
                //不等于-1.0
                if (res != -1.0) {
                    //乘一把
                    return res * edge.val;
                }
            }
            return -1.0;
        }

        //边
        class Edge {
            String from;
            String to;
            double val;

            public Edge(String from, String to, double val) {
                this.from = from;
                this.to = to;
                this.val = val;
            }
        }
    }

    //并查集
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {

        public static void main(String[] args) {
            Solution solution = new Solution();
            List<List<String>> es = new ArrayList<>();
            es.add(Arrays.asList("a", "b"));
            es.add(Arrays.asList("b", "c"));
            es.add(Arrays.asList("bc", "cd"));
            List<List<String>> qs = new ArrayList<>();
            qs.add(Arrays.asList("a", "c"));
            qs.add(Arrays.asList("c", "b"));
            qs.add(Arrays.asList("bc", "cd"));
            qs.add(Arrays.asList("cd", "bc"));
            double[] vs = {1.5, 2.5, 5.0};
            System.out.println(Arrays.toString(solution.calcEquation(es, vs, qs)));
        }

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            int nvars = 0;
            Map<String, Integer> variables = new HashMap<>();

            int n = equations.size();
            for (int i = 0; i < n; i++) {
                if (!variables.containsKey(equations.get(i).get(0))) {
                    variables.put(equations.get(i).get(0), nvars++);
                }
                if (!variables.containsKey(equations.get(i).get(1))) {
                    variables.put(equations.get(i).get(1), nvars++);
                }
            }
            int[] f = new int[nvars];
            double[] w = new double[nvars];
            Arrays.fill(w, 1.0);
            for (int i = 0; i < nvars; i++) {
                f[i] = i;
            }

            for (int i = 0; i < n; i++) {
                int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
                merge(f, w, va, vb, values[i]);
            }
            int queriesCount = queries.size();
            double[] ret = new double[queriesCount];
            for (int i = 0; i < queriesCount; i++) {
                List<String> query = queries.get(i);
                double result = -1.0;
                if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                    int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                    int fa = findf(f, w, ia), fb = findf(f, w, ib);
                    if (fa == fb) {
                        result = w[ia] / w[ib];
                    }
                }
                ret[i] = result;
            }
            return ret;
        }

        public void merge(int[] f, double[] w, int x, int y, double val) {
            int fx = findf(f, w, x);
            int fy = findf(f, w, y);
            f[fx] = fy;
            w[fx] = val * w[y] / w[x];
        }

        public int findf(int[] f, double[] w, int x) {
            if (f[x] != x) {
                int father = findf(f, w, f[x]);
                w[x] = w[x] * w[f[x]];
                f[x] = father;
            }
            return f[x];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
