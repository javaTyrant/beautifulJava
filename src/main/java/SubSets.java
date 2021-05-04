import java.util.ArrayList;
import java.util.List;

/**
 * @author lumac
 * @since 2020/7/7
 */
public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, 0, new ArrayList<>());
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, int cur, List<Integer> list) {
        res.add(new ArrayList<>(list));
        //i = cur
        for (int i = cur; i < nums.length; ++i) {
            list.add(nums[i]);
            //i+1和cur+1的区别
            //i + 1
            dfs(nums, res, i + 1, list);
            //删除最后一个
            list.remove(list.size() - 1);
        }
    }
}
