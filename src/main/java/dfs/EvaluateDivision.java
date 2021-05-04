package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author lumac
 * @since 2020/5/31
 */
public class EvaluateDivision {
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
            List<Edge> v1Edges = mEdges.computeIfAbsent(v1, k -> new ArrayList<>());
            v1Edges.add(new Edge(v1, v2, val));
            // 添加到 v2->v1 的边
            List<Edge> v2Edges = mEdges.computeIfAbsent(v2, k -> new ArrayList<>());
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
            if (visited.contains(edge.to)) {
                continue;
            }
            double res = dfs(edge.to, dest, visited);
            if (res != -1.0) {
                return res * edge.val;
            }
        }
        return -1.0;
    }

    static class Edge {
        String from;
        String to;
        double val;

        public Edge(String from, String to, double val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        List<List<String>> eq = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> querys =
                Arrays.asList(
                        Arrays.asList("a", "c"),
                        Arrays.asList("b", "a"),
                        Arrays.asList("a", "e"),
                        Arrays.asList("a", "a"),
                        Arrays.asList("x", "x"));

        EvaluateDivision division = new EvaluateDivision();
        System.out.println(Arrays.toString(division.calcEquation(eq, values, querys)));
    }
}
