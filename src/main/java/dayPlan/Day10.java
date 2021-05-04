package dayPlan;

import treenode.Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2021/3/5
 */
public class Day10 implements Tree {
    //
    public int largestRectangleAreaBruteForce(int[] heights) {
        int len = heights.length;
        // 特判
        if (len == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            // 找左边最后 1 个大于等于 heights[i] 的下标
            int left = i;
            //当前的高度
            int curHeight = heights[i];
            while (left > 0 && heights[left - 1] >= curHeight) {
                left--;
            }

            // 找右边最后 1 个大于等于 heights[i] 的索引
            int right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }
            int width = right - left + 1;
            res = Math.max(res, width * curHeight);
        }
        return res;
    }

    public static void main(String[] args) {
        Day10 day10 = new Day10();
        Integer[] arr = {-10, 9, 20, null, null, 15, 7};
        TreeNode tree = day10.of(arr);
        System.out.println(day10.maxPathSum(tree));
        System.out.println(day10.multiply("139", "548"));
        System.out.println(day10.multiply("999", "999"));
        System.out.println(day10.multiply("1", "1"));
        System.out.println(day10.multiply("9", "9"));
        System.out.println(day10.multiply("0", "0"));
        int[] height = {2, 1, 5, 6, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println("area" + day10.largestRectangleAreaLessCode(height));
    }

    //下一个排列
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int i = nums.length - 2;
        // 找到第一个下降点，我们要把这个下降点的值增加一点点
        // 对于511这种情况，要把前面两个1都跳过，所以要包含等于
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
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

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public int largestRectangleAreaLessCode(int[] heights) {
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);

        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i < tmp.length; i++) {
            //当栈不为空,且当前的高度小于栈里的高度:为什么呢?
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                System.out.println("i:" + i + " height:" + h + " width:" + (i - stack.peek() - 1));
                area = Math.max(area, (i - stack.peek() - 1) * h);
            }
            stack.push(i);
        }

        return area;
    }

    //柱状图中最大的矩形
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) {
            return 0;
        }
        int[] left = new int[n];
        int[] right = new int[n];

        //第一根柱子，左边不存在比它小的
        left[0] = -1;
        //最后一根柱子，右边不存在比它小的
        right[n - 1] = n;

        for (int i = 1; i < n; i++) {
            int temp = i - 1;
            while (temp >= 0 && heights[temp] >= heights[i]) {
                temp = left[temp];
            }
            //当上述循环 break 后，  temp 即为左边第一根小于 i 位置的柱子
            left[i] = temp;
        }

        for (int i = n - 2; i >= 0; i--) {
            int temp = i + 1;
            while (temp < n && heights[temp] >= heights[i]) {
                temp = right[temp];
            }
            //当上述循环 break 后，  temp 即为左边第一根小于 i 位置的柱子
            right[i] = temp;
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }
        return maxArea;
    }

    //105. 从前序与中序遍历序列构造二叉树
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, 0);
    }

    private TreeNode build(int[] preorder, int preLeft, int preRight, int inLeft) {
        //base case
        if (preRight < preLeft) return null;
        //获取根的位置
        int rootPos = map.get(preorder[preLeft]);
        //获取左子树的大小
        int leftSize = rootPos - inLeft;
        //构造当前的根节点
        TreeNode cur = new TreeNode(preorder[preLeft]);
        //左右构造
        cur.left = build(preorder, preLeft + 1, preLeft + leftSize, inLeft);
        cur.right = build(preorder, preLeft + leftSize + 1, preRight, rootPos + 1);
        return cur;
    }

    //43. 字符串相乘
    public String multiply(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        int[] data = new int[m + n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                data[data.length - (i + j) - 2] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        //
        for (int i = 0; i < data.length - 1; i++) {
            int carry = data[i] / 10;
            data[i] = data[i] % 10;
            data[i + 1] += carry;
        }
        StringBuilder sb = new StringBuilder();
        int i = data.length - 1;
        while (i > 0 && data[i] == 0) {
            i--;
        }
        for (int j = 0; j <= i; j++) {
            sb.append(data[j]);
        }
        return sb.reverse().toString();
    }

    //124. 二叉树中的最大路径和
    private int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        getMax(root);
        return res;
    }

    private int getMax(TreeNode r) {
        //base case
        if (r == null) return 0;
        //left
        int left = Math.max(0, getMax(r.left));
        //right
        int right = Math.max(0, getMax(r.right));
        //取最大,val可能是负数
        res = Math.max(res, r.val + left + right);
        //返回的值
        return Math.max(left, right) + r.val;
    }
}
