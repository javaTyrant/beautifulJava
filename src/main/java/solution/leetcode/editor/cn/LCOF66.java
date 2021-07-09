package solution.leetcode.editor.cn;

import java.util.*;

/**
 * @author lumac
 * @since 2020/12/17
 */
public class LCOF66 {
    //指 Offer 66. 构建乘积数组:这个思路太牛逼了.
    public static int[] constructArr(int[] a) {
        int[] res = new int[a.length];
        for (int i = 0, cur = 1; i < a.length; i++) {
            res[i] = cur;   // 先乘左边的数(不包括自己)
            cur *= a[i];
        }
        for (int i = a.length - 1, cur = 1; i >= 0; i--) {
            res[i] *= cur;  // 再乘右边的数(不包括自己)
            cur *= a[i];
        }
        return res;
    }

    public static void main(String[] args) {
        //res[0] = 1, cur = 1 * 1 = 1
        //res[1] = 1, cur = 1 * 2 = 2
        //res[2] = 2, cur = 2 * 3 = 6
        //res[3] = 6, cur = 6 * 4 = 24
        //res[4] = 24 cur = 24 * 5 = 120
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(constructArr(arr)));
    }

    //剑指 Offer 65. 不用加减乘除做加法
    public int add(int a, int b) {
        return b == 0 ? a : add(a ^ b, (a & b) << 1);
    }

    //求1+2+…+n
    public int sumNums(int n) {
        boolean flag = (n > 0) && (n += sumNums(n - 1)) > 0;
        return n;
    }

    //单源最短路径:要补充下知识点了.
    //给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
    //例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
    //现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
    //求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> rec = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> list = rec.getOrDefault(site, new ArrayList<>());
                for (int j : list) {
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                rec.put(site, list);
            }
        }
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> que = new LinkedList<>();
        for (int site : rec.getOrDefault(source, new ArrayList<>())) {
            dis[site] = 1;
            que.offer(site);
        }
        while (!que.isEmpty()) {
            int x = que.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && dis[y] == -1) {
                    dis[y] = dis[x] + 1;
                    que.offer(y);
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int site : rec.getOrDefault(target, new ArrayList<>())) {
            if (dis[site] != -1) {
                ret = Math.min(ret, dis[site]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }


}
