package dayPlan;

import java.util.*;

/**
 * @author lumac
 * @since 2021/2/23
 */
public class Day1 {
    //不用sb,更加精致
    public String reverseWords(String s) {
        char[] S = s.toCharArray();
        int slow = 0, fast = 0;
        while (fast <= S.length) {
            if (fast == S.length || S[fast] == ' ') {
                swap(S, slow, fast - 1);
                slow = fast + 1;
            }
            fast++;
        }
        return new String(S);
    }

    //交换
    private void swap(char[] s, int start, int end) {
        while (start < end) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;
        }
    }

    //虽然是简单题
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        //判断无输入
        int index = 0;//标记计数
        for (int i = 0; i < nums.length; i++) {
            //如果当前的数和number位置不相等
            if (nums[i] != nums[index]) {
                //index++
                index++;
                //number位置重新赋值
                nums[index] = nums[i];
            }
        }
        //index从0开始的
        return index + 1;
    }

    //最多允许两个重复
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            //i < 2或者当前的大于前面的
            if (i < 2 || nums[j] > nums[i - 2]) {
                //赋值然后++
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    //原地修改
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //基本判断
        if (obstacleGrid == null || obstacleGrid[0].length == 0) {
            return 0;
        }
        //rol
        int rol = obstacleGrid.length;
        //col
        int col = obstacleGrid[0].length;

        //能不能想全了
        for (int i = 0; i < rol; i++) {  //这里表示行下的某列
            for (int j = 0; j < col; j++) {
                //
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    obstacleGrid[i][j] = 1;
                } else if (i == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1];
                } else if (j == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j];
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        }
        return obstacleGrid[rol - 1][col - 1];
    }

    //网格中的障碍物和空位置分别用 1 和 0 来表示。
    public int uniquePathsWithObstaclesNotChange(int[][] obstacleGrid) {
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[] dp = new int[col];

        //
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int[] ints : obstacleGrid) {
            for (int j = 0; j < col; ++j) {
                //如果遇到障碍物
                if (ints[j] == 1) {
                    //赋值0,如果只有一行,也遇到了一个1,那么也直接设置成0.
                    dp[j] = 0;
                    //回到循环
                    continue;
                }
                //如果j > 1 且当前的点是0
                if (j - 1 >= 0 && ints[j - 1] == 0) {
                    //累加
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[col - 1];
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    //中序遍历
    private Node pre, head;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        //先dfs
        dfs(root);
        //连接起来
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void dfs(Node root) {
        //base case
        if (root == null) return;
        //left
        dfs(root.left);
        //如果pre==null,都指向root
        if (pre == null) {
            pre = root;
            head = root;
        } else {
            //pre.right
            //root.left
            pre.right = root;
            root.left = pre;
            //pre走一步
            pre = pre.right;
        }
        //right
        dfs(root.right);
    }

    public int translateNum(int num) {
        System.out.println("num:" + num);
        //base case,数字小于9只有一种
        if (num <= 9) return 1;
        //模100
        int ba = num % 100;
        return (ba <= 9 || ba >= 26) ? translateNum(num / 10) : translateNum(num / 10) + translateNum(num / 100);
    }

    public int translateNumDp(int num) {
        String src = String.valueOf(num);
        int p, q = 0, r = 1;
        for (int i = 0; i < src.length(); ++i) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            //
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                r += p;
            }
        }
        return r;
    }


    //
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n < 0) return res;
        dfs(new char[n * 2], 0, n, n, res);
        return res;
    }

    private void dfs(char[] chars, int idx, int leftRemain, int rightRemain, List<String> res) {
        //idx == chars.length
        if (idx == chars.length) {
            res.add(new String(chars));
            return;
        }

        if (leftRemain > 0) {
            //放左边
            chars[idx] = '(';
            dfs(chars, idx + 1, leftRemain - 1, rightRemain, res);
        }
        //如果左右相等不能先放),放了就无法闭环了
        if (rightRemain > 0 && leftRemain != rightRemain) {
            //放右边
            chars[idx] = ')';
            dfs(chars, idx + 1, leftRemain, rightRemain - 1, res);
        }
    }

    //149. 直线上最多的点数
    public static int maxPoints(int[][] points) {
        int n = points.length;
        if (n == 0) return 0;
        if (n == 1) return 1;
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            //
            Map<String, Integer> slope = new HashMap<>();
            int repeat = 0;
            int tmpMax = 0;
            for (int j = i + 1; j < n; j++) {
                int dy = points[i][1] - points[j][1];
                int dx = points[i][0] - points[j][0];
                if (dy == 0 && dx == 0) {
                    repeat++;
                    continue;
                }
                int g = gcd(dy, dx);
                if (g != 0) {
                    dy /= g;
                    dx /= g;
                }
                String tmp = dy + "/" + dx;
                slope.put(tmp, slope.getOrDefault(tmp, 0) + 1);
                tmpMax = Math.max(tmpMax, slope.get(tmp));
            }
            res = Math.max(res, repeat + tmpMax + 1);
        }
        return res;
    }

    //最大公约数
    private static int gcd(int y, int x) {
        if (x == 0) return y;
        else return gcd(x, y % x);
    }

    //构建乘积数组:思路太牛逼了.
    public static int[] constructArr(int[] a) {
        //结果集
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
        Day1 solution = new Day1();
        System.out.println(solution.reverseWords("hello this is my"));
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] arr1 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        solution.removeDuplicates(arr);
        solution.removeDuplicates2(arr1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        System.out.println(solution.translateNum(1225865565));
        System.out.println(solution.translateNumDp(12258));
        System.out.println(solution.generateParenthesis(3));
        int[][] points = {
                {1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}
        };
        System.out.println(maxPoints(points));
        int[] arr8 = {1, 2, 8, 4};
        System.out.println(Arrays.toString(constructArr(arr8)));
    }

    //741. 摘樱桃
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        int[][] dp = new int[N][N];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        dp[0][0] = grid[0][0];

        // 一共要走 2 * N - 2 步，满足横纵坐标之和为 t
        for (int t = 1; t <= 2 * N - 2; t++) {
            int[][] dp2 = new int[N][N];
            for (int[] row : dp2) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }

            // 枚举横坐标
            for (int i = Math.max(0, t - (N - 1)); i <= Math.min(N - 1, t); i++) {
                // 枚举纵坐标
                for (int j = Math.max(0, t - (N - 1)); j <= Math.min(N - 1, t); j++) {
                    // 遇到墙
                    if (grid[i][t - i] == -1 || grid[j][t - j] == -1) {
                        continue;
                    }

                    // 否则加上 0 或者加上 1
                    int res = grid[i][t - i];
                    if (i != j) {
                        // 不重合的时候加上另一个坐标
                        res += grid[j][t - j];
                    }

                    // 枚举上一步的坐标
                    for (int pi = i - 1; pi <= i; pi++) {
                        for (int pj = j - 1; pj <= j; pj++) {
                            if (pi >= 0 && pj >= 0) {
                                dp2[i][j] = Math.max(dp2[i][j], dp[pi][pj] + res);
                            }
                        }
                    }
                }
            }
            dp = dp2;
        }
        return Math.max(0, dp[N - 1][N - 1]);
    }
}
