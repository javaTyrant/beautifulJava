package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lumac
 * @since 2020/6/26
 */
public class Subsets {
    //78. 子集
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, 0, new ArrayList<>());
        return res;
    }

    /**
     * @param nums 数据源
     * @param res  结果集
     * @param cur  当前的索引
     * @param list 结果的子集
     */
    private static void dfs(int[] nums, List<List<Integer>> res, int cur, List<Integer> list) {
        res.add(new ArrayList<>(list));
        //cur
        for (int i = cur; i < nums.length; ++i) {
            list.add(nums[i]);
            //i+1和cur+1的区别
            dfs(nums, res, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
