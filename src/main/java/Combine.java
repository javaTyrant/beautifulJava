import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lumac
 * @since 2020/9/8
 */
public class Combine {
    //77. 组合,如何思考比答案更重要
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        //从1开始
        dfs(res, list, n, k, 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> list, int n, int k, int start) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            dfs(res, list, n, k, i + 1);
            list.remove(list.size() - 1);
        }
    }

    //组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        //答案,源,目标,begin,剩余,子答案
        help(res, candidates, target, 0, candidates.length, new ArrayList<>());
        return res;
    }

    private void help(List<List<Integer>> res, int[] candidates, int target, int begin, int len, List<Integer> list) {
        //target到0
        if (target == 0) {
            res.add(new ArrayList<>(list));
        }
        //begin到len
        for (int i = begin; i < len; i++) {
            if (target - candidates[i] < 0) return;
            list.add(candidates[i]);
            //len的长度不变,移动i
            help(res, candidates, target - candidates[i], i, len, list);
            //回溯
            list.remove(list.size() - 1);
        }
    }

    //入: k = 3, n = 9 组合中只允许含有 1 - 9 的正整数
    //输出: [[1,2,6], [1,3,5], [2,3,4]]
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        //i从开始因为是1-9
        dfs(1, 0, n, k, li, res);
        return res;
    }

    public static void dfs(int i, int sum, int n, int k, List<Integer> li, List<List<Integer>> res) {
        //个数用完,且sum累加到n了 base case
        if (k == 0 && sum == n) {        // 什么时候保存结果
            res.add(new ArrayList<>(li));
            return;
        }
        //剪枝
        if (sum > n) return;
        for (int j = i; j <= (Math.min(n, 9)); j++) {
            li.add(j);                  // 选择一个数
            dfs(j + 1, sum + j, n, k - 1, li, res);   // 选择下一个数
            li.remove(li.size() - 1);     // 撤销一个数
        }
    }

    public static void main(String[] args) {
        System.out.println(0x0);
        System.out.println(0xffffffffL);
        Combine combine = new Combine();
        System.out.println(combine.combine(4, 2));
        System.out.println("".equalsIgnoreCase(null));
        System.out.println(BigDecimal.valueOf(-1.11).intValue());
    }
}
