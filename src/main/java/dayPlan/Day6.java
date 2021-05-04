package dayPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lumac
 * @since 2021/2/28
 */
public class Day6 {
    //组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //先排序
        Arrays.sort(candidates);
        //res
        List<List<Integer>> res = new ArrayList<>();
        //答案,源,目标,begin,剩余,子答案
        help1(res, candidates, target, 0, candidates.length, new ArrayList<>());
        return res;
    }

    private void help1(List<List<Integer>> res, int[] candidates, int target, int begin, int len, List<Integer> list) {
        //base case
        if (target == 0) {
            res.add(new ArrayList<>(list));
        }
        //循环的条件
        for (int i = begin; i < len; i++) {
            //小于0return掉
            if (target - candidates[i] < 0) return;
            //add进去
            list.add(candidates[i]);
            //还是从i开始
            help1(res, candidates, target - candidates[i], i, len, list);
            //remove
            list.remove(list.size() - 1);
        }
    }

    //组合总和2,只能用一次
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //先排序
        Arrays.sort(candidates);
        //res
        List<List<Integer>> res = new ArrayList<>();
        //答案,源,目标,begin,剩余,子答案
        help(res, candidates, target, 0, candidates.length, new ArrayList<>());
        return res;
    }

    private void help(List<List<Integer>> res, int[] candidates, int target, int begin, int len, List<Integer> list) {
        //base case
        if (target == 0) {
            res.add(new ArrayList<>(list));
        }
        //循环的条件
        for (int i = begin; i < len; i++) {
            //小于0return掉
            if (target - candidates[i] < 0) return;
            //如果i > begin且当前的和之前的相等,continue
            if (i > begin && candidates[i] == candidates[i - 1]) continue;
            //add进去
            list.add(candidates[i]);
            //还是从i开始
            help(res, candidates, target - candidates[i], i + 1, len, list);
            //remove
            list.remove(list.size() - 1);
        }
    }
    //如果每个数字可以用两次呢

    //组合总和 III
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(1, 0, k, n, res, cur);
        return res;
    }

    private void dfs(int i, int sum, int k, int n, List<List<Integer>> res, List<Integer> cur) {
        //base case
        if (k == 0 && sum == n) {
            res.add(new ArrayList<>(cur));
        }
        if (sum > n) return;
        for (int j = i; j <= (Math.min(9, n)); j++) {
            cur.add(j);
            dfs(j + 1, sum + j, k - 1, n, res, cur);
            cur.remove(cur.size() - 1);
        }
    }

    //377. 组合总和 Ⅳ
    public int combinationSum4(int[] nums, int target) {
        //被之前的逻辑误导了呀
        int[] memo = new int[target + 1];
        memo[0] = 1;
        for (int i = 0; i < target; i++) {
            for (int num : nums) {
                if (i + num <= target) {
                    memo[i + num] += memo[i];
                }
            }
        }
        return memo[target];
    }

    //子集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        find(res, nums, 0, new ArrayList<>());
        return res;
    }

    private void find(List<List<Integer>> res, int[] nums, int index, List<Integer> cur) {
        res.add(new ArrayList<>(cur));
        //base case隐藏在for循环里
        for (int i = index; i < nums.length; i++) {
            cur.add(nums[i]);
            find(res, nums, i + 1, cur);
            cur.remove(cur.size() - 1);
        }
    }

    //复原IP地址
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        back(s, ans, new ArrayList<>(), 0);
        return ans;
    }

    private void back(String s, List<String> ans, List<String> cur, int pos) {
        //base case
        if (cur.size() == 4) {
            if (s.length() == pos) {
                ans.add(String.join(".", cur));
            }
            return;
        }
        //
        for (int i = 1; i <= 3; i++) {
            //边界检查
            if (pos + i > s.length()) break;
            //取字符串
            String seg = s.substring(pos, pos + i);
            //大于1不能以0开头,==3不能大于255
            if (seg.startsWith("0") && seg.length() > 1 || (i == 3 && Integer.parseInt(seg) > 255))
                continue;
            //add
            cur.add(seg);
            //注意是+i 不是加1
            //
            back(s, ans, cur, pos + i);
            //remove
            cur.remove(cur.size() - 1);
        }
    }

    //分割回文串
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            int j;
            for (int i = 0; (j = i + len - 1) < n; i++)
                dp[i][j] = len == 1 || s.charAt(i) == s.charAt(j) && (len == 2 || dp[i + 1][j - 1]);
        }
        List<List<String>> ans = new ArrayList<>();
        dfs(s, dp, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(String s, boolean[][] dp, int begin, List<String> buffer, List<List<String>> ans) {
        if (begin == s.length()) {
            ans.add(new ArrayList<>(buffer));
            return;
        }
        int end;
        for (int len = 1; (end = begin + len - 1) < s.length(); len++) {
            if (dp[begin][end]) {
                buffer.add(s.substring(begin, end + 1));
                dfs(s, dp, begin + len, buffer, ans);
                buffer.remove(buffer.size() - 1);
            }
        }
    }

    //33. 搜索旋转排序数组
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[right]) {//右边有序
                //右边是有序的,
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] >= nums[right]) {//左边有序
                //判断是否在左边,左边是有序的,上下界可以确定
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    //279. 完全平方数
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (i >= j * j) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    //
    public static void main(String[] args) {
        int[] candidate = {2, 3, 6, 7};
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        Day6 day6 = new Day6();
        System.out.println(day6.numSquares(25));
        System.out.println(day6.search(arr, 0));
        System.out.println(day6.combinationSum(candidate, 7));
    }
}
