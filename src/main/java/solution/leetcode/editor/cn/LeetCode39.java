package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode39 {
    static class Solution {
        public static List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> res = new ArrayList<>();
            //答案,源,目标,begin,剩余,子答案
            help(res, candidates, target, 0, candidates.length, new ArrayList<>());
            return res;
        }

        //怎么确保一个数可以用多次呢,通过i回溯的i的位置
        private static void help(List<List<Integer>> res, int[] candidates, int target, int begin, int len, List<Integer> list) {
            if (target == 0) {
                res.add(new ArrayList<>(list));
            }
            //begin len不变
            for (int i = begin; i < len; i++) {
                //小于0 return
                if (target - candidates[i] < 0) return;
                list.add(candidates[i]);
                //target - candidates[i]
                help(res, candidates, target - candidates[i], i, len, list);
                list.remove(list.size() - 1);
            }
        }

        public static void main(String[] args) {
            int[] candi = {2, 4, 6, 8};
            System.out.println(combinationSum(candi, 8));
        }

    }
}
