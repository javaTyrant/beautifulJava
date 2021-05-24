package conquerdp.offer;

/**
 * @author lufengxiang
 * @since 2021/5/21
 **/
public class Search {
    //二维数组中的查找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        //参数校验
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        //
        int m = matrix.length, n = matrix[0].length;
        //0 n-1
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            //右上角开始找
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }
}
