package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode448 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            boolean[] exist = new boolean[nums.length + 1];
            //先保存所有存在的数据
            for (int i : nums) {
                exist[i] = true;
            }
            List<Integer> list = new ArrayList<>();
            //
            for (int i = 1; i <= nums.length; i++) {
                if (!exist[i]) list.add(i);
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
