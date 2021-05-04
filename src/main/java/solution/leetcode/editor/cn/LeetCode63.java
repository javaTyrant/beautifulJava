package solution.leetcode.editor.cn;

public class LeetCode63 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //原地修改,不要dp,要问清楚可不可以原地修改
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
                    //障碍为0
                    if (obstacleGrid[i][j] == 1) {
                        obstacleGrid[i][j] = 0;
                        //注意
                        continue;
                    }
                    //都等于0,不会出现obstacleGrid[i][j] == 1的情况
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
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = {{1, 0}};
        System.out.println(solution.uniquePathsWithObstacles(arr));
    }
//leetcode submit region end(Prohibit modification and deletion)

}
