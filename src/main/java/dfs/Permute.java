package dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lumac
 * @since 2020/7/13
 */
public class Permute {
    //全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> datas = new ArrayList<>();
        //数组转列表
        for (int num : nums) {
            datas.add(num);
        }
        //
        dfs(datas, res, 0, nums.length);
        return res;
    }

    /**
     * 需要几个参数呢
     *
     * @param nums
     * @param res
     * @param index
     * @param len
     */
    private void dfs(List<Integer> nums, List<List<Integer>> res, int index, int len) {
        //添加条件
        if (index == len) {
            res.add(new ArrayList<>(nums));
        } else {
            //循环递归
            for (int i = index; i < len; i++) {
                //交换
                Collections.swap(nums, index, i);
                dfs(nums, res, index + 1, len);
                //恢复
                Collections.swap(nums, index, i);
            }
        }
    }
}
