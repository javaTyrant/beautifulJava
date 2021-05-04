package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode207 {
    static class SolutionDfs {
        public static void main(String[] args) {
            SolutionDfs solution = new SolutionDfs();
            int[][] cos = {
                    {1, 2},
                    {2, 0},
                    {0, 1}
            };
            System.out.println(solution.canFinishDfs(3, cos));
        }

        //深度优先
        List<List<Integer>> edges;
        //记录是否被访问过
        int[] visited;
        boolean valid = true;

        public boolean canFinishDfs(int numCourses, int[][] prerequisites) {
            edges = new ArrayList<>();
            for (int i = 0; i < numCourses; ++i) {
                edges.add(new ArrayList<>());
            }
            visited = new int[numCourses];
            for (int[] cp : prerequisites) {
                edges.get(cp[1]).add(cp[0]);
            }
            //到这里都是相同的操作
            for (int i = 0; i < numCourses && valid; ++i) {
                if (visited[i] == 0) {
                    dfs(i);
                }
            }
            return valid;
        }

        public void dfs(int u) {
            //设置u,说明u已经处理了
            visited[u] = 1;
            //获取u的后续课程
            for (int v : edges.get(u)) {
                //如果v没有处理过
                if (visited[v] == 0) {
                    //递归处理v
                    dfs(v);
                    //false返回
                    if (!valid) {
                        return;
                    }
                    //v被处理过了,有环了
                } else if (visited[v] == 1) {
                    //返回
                    valid = false;
                    return;
                }
            }
            //u处理完
            visited[u] = 2;
        }
    }

    static class Mysolution {
        public static void main(String[] args) {
            Mysolution solution = new Mysolution();
            int[][] cos = {
                    {4, 0},
                    {4, 1},
                    {3, 1},
                    {3, 2},
                    {5, 4},
                    {5, 3},
            };
            System.out.println(solution.canFinish(6, cos));
        }

        public static boolean canFinish(int numCourses, int[][] prerequisites) {
            //
            int[] indgree = new int[numCourses];
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] cp : prerequisites) {
                indgree[cp[0]]++;
                adj.get(cp[1]).add(cp[0]);
            }
            Queue<Integer> queue = new LinkedList<>();
            //    for (int i = 0; i < numCourses; i++)
            //        if (indgree[i] == 0) queue.add(i);
            //存的是索引不是值哦
            for (int i : indgree) {
                if (i == 0) queue.offer(i);
            }
            while (!queue.isEmpty()) {
                Integer cur = queue.poll();
                numCourses--;
                for (int c : adj.get(cur)) {
                    if (--indgree[c] == 0) queue.offer(c);
                }
            }
            return numCourses == 0;
        }
    }

    static class Solution2 {
        public static void main(String[] args) {

        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int index = 0;
            int[] degree = new int[numCourses];
            int[] ans = new int[numCourses];
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] pre : prerequisites) {
                degree[pre[0]]++;
                adj.get(pre[1]).add(pre[0]);
            }
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < degree.length; i++) {
                if (degree[i] == 0) {
                    q.offer(i);
                }
            }
            while (!q.isEmpty()) {
                int a = q.poll();
                List<Integer> e = adj.get(a);
                for (int i : e) {
                    degree[i]--;
                    if (degree[i] == 0) {
                        q.offer(i);
                    }
                }
                ans[index] = a;
                index++;
            }
            return index != numCourses ? new int[0] : ans;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //课程表
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            //入度表,指向该点的边数量
            int[] indegrees = new int[numCourses];
            //邻接表
            List<List<Integer>> adjacency = new ArrayList<>();
            //队列
            Queue<Integer> queue = new LinkedList<>();
            //初始化邻接表
            for (int i = 0; i < numCourses; i++)
                adjacency.add(new ArrayList<>());
            //添加邻接表和入度表
            for (int[] cp : prerequisites) {
                //入度表,被依赖的课程入度
                indegrees[cp[0]]++;
                //依赖的课程入邻接表
                adjacency.get(cp[1]).add(cp[0]);
            }
            //先把入度为0加到queue里
            for (int i = 0; i < numCourses; i++)
                if (indegrees[i] == 0) queue.add(i);
            // BFS TopSort.
            while (!queue.isEmpty()) {
                //入度为0的课程
                int pre = queue.poll();
                //学完减1
                numCourses--;
                //获取依赖pre的课程
                for (int cur : adjacency.get(pre))
                    //入度为0,再减
                    if (--indegrees[cur] == 0) {
                        queue.add(cur);
                    }
            }
            return numCourses == 0;
        }

        //深度优先
        List<List<Integer>> edges;
        int[] visited;
        boolean valid = true;

        public boolean canFinishDfs(int numCourses, int[][] prerequisites) {
            edges = new ArrayList<>();
            for (int i = 0; i < numCourses; ++i) {
                edges.add(new ArrayList<>());
            }
            visited = new int[numCourses];
            for (int[] cp : prerequisites) {
                edges.get(cp[1]).add(cp[0]);
            }
            //到这里都是相同的操作
            for (int i = 0; i < numCourses && valid; ++i) {
                if (visited[i] == 0) {
                    dfs(i);
                }
            }
            return valid;
        }

        public void dfs(int u) {
            visited[u] = 1;
            for (int v : edges.get(u)) {
                if (visited[v] == 0) {
                    dfs(v);
                    if (!valid) {
                        return;
                    }
                } else if (visited[v] == 1) {
                    valid = false;
                    return;
                }
            }
            visited[u] = 2;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[][] cos = {
                    {4, 0},
                    {4, 1},
                    {3, 1},
                    {3, 2},
                    {5, 4},
                    {5, 3},
            };
            System.out.println(solution.canFinish(6, cos));
            System.out.println(solution.canFinishDfs(6, cos));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
