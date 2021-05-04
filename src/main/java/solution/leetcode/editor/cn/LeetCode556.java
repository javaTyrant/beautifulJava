package solution.leetcode.editor.cn;

public class LeetCode556 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //556. 下一个更大元素 III 这解法太牛逼了
        //解题思路:
        public int nextGreaterElement(int n) {
            char[] chars = String.valueOf(n).toCharArray();
            for (int i = chars.length - 1; i > 0; --i) {
                //如果前一个大于当前,一直往前找,极端的321,说明这个数已经是最大的,return -1
                if (chars[i - 1] >= chars[i]) continue;
                //说明找到了前一个大于当前的数
                --i;
                int j = i;
                //边界判断 && 找到最后一个大于chars[i]的j的位置
                while (j != chars.length - 1 && chars[j + 1] > chars[i]) ++j;
                //先交换一次
                swap(chars, i, j);
                //怎么能推导出k的边界呢?
                //先搞懂这个是什么意思(chars.length - 1 - i): i后面的数组长度
                //因为要两两交换所以要求出一半的距离,最后再加上i的偏移量
                for (int k = i + 1; k <= i + (chars.length - 1 - i) / 2; ++k) {
                    System.out.println(k + ":" + (chars.length + i - k));
                    //交换的位置,这个好推理
                    swap(chars, k, chars.length + i - k);
                }
                try {
                    return Integer.parseInt(new String(chars));
                } catch (Exception e) {
                    return -1;
                }
            }
            return -1;
        }

        private void swap(char[] chars, int i, int j) {
            char t = chars[i];
            chars[i] = chars[j];
            chars[j] = t;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.nextGreaterElement(1421));
            //执行路径写一下
            System.out.println(solution.nextGreaterElement(158476531));
            System.out.println(solution.nextGreaterElement(18762));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
