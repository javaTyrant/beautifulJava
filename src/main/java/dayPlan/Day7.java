package dayPlan;

import treenode.Tree;

import java.util.*;

/**
 * @author lumac
 * @since 2021/3/1
 */
public class Day7 implements Tree {
    //98. 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validate(TreeNode node, long min, long max) {
        //base case
        if (node == null) {
            return true;
        }
        //搜索树校验
        if (node.val <= min || node.val >= max) {
            return false;
        }
        //左右两次验证
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    //不同路径
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //边界都是1
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else {
                    //否则累加
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    //338. 比特位计数
    public int[] countBits(int num) {
        int dp[] = new int[num + 1];
        for (int i = 0; i <= num / 2; i++) {
            //偶数位:和前面的相等
            dp[i * 2] = dp[i];
            if (i * 2 + 1 <= num)
                dp[i * 2 + 1] = dp[i] + 1;
        }
        return dp;
    }

    //14. 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        //参数校验
        if (strs == null || strs.length == 0) return "";
        //先取第一个最长的
        String res = strs[0];
        //从1开始
        for (int i = 1; i < strs.length; i++) {
            //完全符合==0
            while (strs[i].indexOf(res) != 0) {
                //sub一个字符
                res = res.substring(0, res.length() - 1);
                //如果res为空,返回空,否则继续处理
                if (res.isEmpty()) return res;
            }
        }
        return res;
    }

    //31. 下一个排列
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int i = nums.length - 2;
        // 找到第一个下降点，我们要把这个下降点的值增加一点点
        // 对于511这种情况，要把前面两个1都跳过，所以要包含等于
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }//此时i是小于i+1位置的

        // 如果这个下降点还在数组内，我们找到一个比它稍微大一点的数替换
        // 如果在之外，说明整个数组是降序的，是全局最大了
        if (i >= 0) {
            int j = nums.length - 1;
            // 对于151，这种情况，要把最后面那个1跳过，所以要包含等于
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 将下降点之前的部分倒序构成一个最小序列
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    //交换全部的大小
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    //滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数按从大到小排序
        LinkedList<Integer> list = new LinkedList();
        // 结果数组
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小 弹出
            while (!list.isEmpty() && nums[list.peekLast()] <= nums[i]) {
                list.pollLast();
            }
            // 添加当前值对应的数组下标
            list.addLast(i);
            // 初始化窗口 等到窗口长度为k时 下次移动在删除过期数值
            if (list.peek() <= i - k) {
                list.poll();
            }
            // 窗口长度为k时 再保存当前窗口public int[] maxSlidingWindow(int[] nums, int k) {
            //         if(nums==null||nums.length<2) return nums;
            //        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数按从大到小排序
            //        LinkedList<Integer> list = new LinkedList();
            //        // 结果数组
            //        int[] result = new int[nums.length-k+1];
            //        for(int i=0;i<nums.length;i++){
            //            // 保证从大到小 如果前面数小 弹出
            //            while(!list.isEmpty()&&nums[list.peekLast()]<=nums[i]){
            //                list.pollLast();
            //            }
            //            // 添加当前值对应的数组下标
            //            list.addLast(i);
            //            // 初始化窗口 等到窗口长度为k时 下次移动在删除过期数值
            //            if(list.peek()<=i-k){
            //                list.poll();
            //            }
            //            // 窗口长度为k时 再保存当前窗口中最大值
            //            if(i-k+1>=0){
            //                result[i-k+1] = nums[list.peek()];
            //            }
            //        }
            //        return result;
            //    }中最大值
            if (i - k + 1 >= 0) {
                result[i - k + 1] = nums[list.peek()];
            }
        }
        return result;
    }

    //二叉树的右视图,递归
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    //时间复杂度O(n)
    private void dfs(TreeNode node, List<Integer> res, int size) {
        //base case
        if (node == null) return;
        //satisfaction
        if (res.size() == size) {
            res.add(node.val);
        }
        //右加1再左加1
        dfs(node.right, res, size + 1);
        dfs(node.left, res, size + 1);
    }

    public List<Integer> rightView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                //先左后右
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    //二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        //这个指针是干啥的
        TreeNode prev = null;//跟踪是不是已经遍历过了
        while (root != null || !stack.isEmpty()) {
            //先把left全部加进去
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //弹出来
            root = stack.pop();
            //right为空,或者right == prev
            // root.right == prev是什么意思
            // it means当前的节点是不是root的右节点,如果是的,说明已经遍历完了
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                //
                root = null;
            } else {
                //push回去
                stack.push(root);
                //指针往右转移
                root = root.right;
            }
        }
        return res;
    }

    //最长有效括号
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    //12. 整数转罗马数字:时间复杂度：O(1)
    public String intToRoman(int num) {
        //维护两个数组
        int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] dic = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        //sb
        StringBuilder sb = new StringBuilder();
        //循环:遍历numbers
        for (int i = 0; i < numbers.length; i++) {
            //找到最大的值,如果num大于
            if (num >= numbers[i]) {
                //判断需要处理几次:除一下
                int count = num / numbers[i];
                //再循环count
                for (int j = 0; j < count; j++) {
                    sb.append(dic[i]);
                    //减:
                    num = num - numbers[i];
                }
            }
        }
        return sb.toString();
    }

    //整数转罗马数字
    public int romanToInt(String s) {
        //不用map,写死返回会更快
        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
        int res = 0;
        //遍历s
        for (int i = 0; i < s.length(); i++) {
            //nextVal:不越界就是s.charAt(i+1):0
            int nextVal = i < s.length() - 1 ? map.get(s.charAt(i + 1)) : 0;
            //获取当前的
            int cur = map.get(s.charAt(i));
            //加还是减
            res += nextVal > cur ? -cur : cur;
        }
        return res;
    }

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        //参数校验
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        //res map digits index,sb
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations,
                          Map<Character, String> phoneMap,
                          String digits,
                          int index,
                          StringBuffer combination) {
        //base cases
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }
        //获取数字
        char digit = digits.charAt(index);
        //从数字获取字母
        String letters = phoneMap.get(digit);
        int lettersCount = letters.length();
        //for each lettersCount
        for (int i = 0; i < lettersCount; i++) {
            combination.append(letters.charAt(i));
            //index + 1
            backtrack(combinations, phoneMap, digits, index + 1, combination);
            combination.deleteCharAt(index);
        }
    }

    //岛屿数量
    public int numIslands(char[][] grid) {
        //先判空
        if (grid == null || grid.length == 0) {
            return 0;
        }
        //row
        int nr = grid.length;
        //column
        int nc = grid[0].length;
        //res
        int numIslands = 0;
        //经典的两层遍历
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                //==1再dfs
                if (grid[r][c] == '1') {
                    //++
                    ++numIslands;
                    dfs(grid, r, c);
                }
            }
        }
        return numIslands;
    }

    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        //剪枝,仔细分析一下就出来了,就是几个边界值的判断嘛
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }
        //置零
        grid[r][c] = '0';
        //上下左右,顺序不影响
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    //岛屿周长
    public int islandPerimeter(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    // 题目限制只有一个岛屿，计算一个即可
                    return dfs(grid, r, c);
                }
            }
        }
        return 0;
    }

    int dfs(int[][] grid, int r, int c) {
        if (!(0 <= r && r < grid.length && 0 <= c && c < grid[0].length)) {
            return 1;
        }
        //等于0返回1
        if (grid[r][c] == 0) {
            return 1;
        }
        //不等于1返回0
        if (grid[r][c] != 1) {
            return 0;
        }
        //设置成2
        grid[r][c] = 2;
        return dfs(grid, r - 1, c)
                + dfs(grid, r + 1, c)
                + dfs(grid, r, c - 1)
                + dfs(grid, r, c + 1);
    }

    public static int islandPerimeter1(int[][] grid) {
        //二维数组的宽度
        int len1 = grid.length;
        //二维数组的长度
        int len2 = grid[0].length;
        //周长初始化为0
        int perimeter = 0;
        //开始遍历
        for (int i = 0; i < len1; i++) {
            //只用考虑四种边界,优秀啊
            for (int j = 0; j < len2; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                //当前格子上面是水
                if (i - 1 < 0 || grid[i - 1][j] == 0) {
                    perimeter++;
                }
                //当前格子左边是是
                if (j - 1 < 0 || grid[i][j - 1] == 0) {
                    perimeter++;
                }
                //如果走到右边或者不在最右边且右边的格子是水
                if (i + 1 == len1 || i + 1 < len1 && grid[i + 1][j] == 0) {
                    perimeter++;
                }
                //如果走到最下面或者不在最右边且下面的格子是水
                if (j + 1 == len2 || j + 1 < len2 && grid[i][j + 1] == 0) {
                    perimeter++;
                }
            }
        }
        return perimeter;
    }

    //satisfaction, satisfy, contentment, be satisfied with
    public static void main(String[] args) {
        Day7 day7 = new Day7();
        Integer[] trees = {1, 2, 3, 7, 8, 4, 5};
        TreeNode node = day7.of(trees);
        System.out.println(day7.postorderTraversal(node));
        //int[] arr = {1, 2, 4, 2, 5};
        int[] arr = {1, 5, 8, 5, 1, 3, 6, 4, 7};
        //1.先找到大于最后面数的第一个值
        //2.
        day7.nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(day7.longestCommonPrefix(strs));
        System.out.println(Arrays.toString(day7.countBits(1)));
        System.out.println(Arrays.toString(day7.countBits(2)));
        System.out.println(Arrays.toString(day7.countBits(3)));
        System.out.println(Arrays.toString(day7.countBits(4)));
        System.out.println(Arrays.toString(day7.countBits(5)));
    }
}
