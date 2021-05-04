package dayPlan;

import linklist.Link;
import treenode.Tree;

import java.util.*;

/**
 * @author lumac
 * @since 2021/2/24
 */
public class Day2 implements Link, Tree {
    //求平方根
    public int mySqrt(int x) {
        //
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            //mid
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                //mid+1
                l = mid + 1;
            } else {
                //mid - 1
                r = mid - 1;
            }
        }
        return ans;
    }

    public int mySqrtNewton(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

    //32. 最长有效括号
    public int longestValidParenthesesDp(String s) {
        int maxans = 0;
        //以i结尾的最大长度
        int[] dp = new int[s.length()];
        //从1开始
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                //如果当前是)前一个是(那么就是一个完整的
                if (s.charAt(i - 1) == '(') {
                    //.....()
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    //.....))这个推导要复杂一些,dp[i-1]满足的长度
                    int preIndex = i - dp[i - 1] - 2;
                    if (preIndex >= 0) {
                        dp[i] = dp[i - 1] + dp[preIndex] + 2;
                    } else {
                        dp[i] = dp[i - 1] + 2;
                    }
                    //dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                //取最大的
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    //
    public int longestValidParenthesesStack(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    //左右扫描一次
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

    public static void main(String[] args) {
        Day2 day2 = new Day2();
        System.out.println(day2.longestValidParentheses("(((())"));
        System.out.println(day2.longestValidParenthesesDp("(((())))))()"));
        int[] arr = {2, 1, 1, 6, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(day2.largestRectangleArea(arr));
        int[] arr1 = {1};
        ListNode node = day2.arrayToListNode(arr1);
        day2.getKthFromEnd(node, 1);
    }

    //柱状图中最大的矩形
    @SuppressWarnings("all")
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        //放个-1进去
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            //栈里放的是索引.
            //当前的大于栈里的,说明是递增的,一直往栈里放,直到当前的小于等于栈里的
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                //此时:当前的高度小于栈里的
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1;
                System.out.println("height * width:" + height + "*" + width);
                maxarea = Math.max(maxarea, height * width);
            }
            stack.push(i);
        }
        //此时栈里的数据
        while (stack.peek() != -1) {
            int height = heights[stack.pop()];
            int width = heights.length - stack.peek() - 1;
            System.out.println("l:height * width:" + height + "*" + width);
            maxarea = Math.max(maxarea, height * width);
        }
        return maxarea;
    }

    //977. 有序数组的平方
    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int j = 0;
        while (j < N && A[j] < 0)
            j++;
        int i = j - 1;
        int[] ans = new int[N];
        int t = 0;
        while (i >= 0 && j < N) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }
        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }
        while (j < N) {
            ans[t++] = A[j] * A[j];
            j++;
        }
        return ans;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        //
        ListNode cur = head;
        ListNode slow = head;
        while (cur != null && k > 0) {
            cur = cur.next;
            k--;
        }
        //
        while (cur != null) {
            cur = cur.next;
            slow = slow.next;
        }
        return slow;
    }

    //合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        //从后往前修改就不用辅助空间了,巧妙
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = (nums2[p2] > nums1[p1]) ? nums2[p2--] : nums1[p1--];
        }
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }


    public List<Integer> rightSideViewBfs(TreeNode root) {
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
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {  //将当前层的最后一个节点放入结果列表
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> res, int size) {
        if (node == null) return;
        if (res.size() == size) {
            res.add(node.val);
        }
        dfs(node.right, res, size + 1);
        dfs(node.left, res, size + 1);
    }

    //八皇后
    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> res = new ArrayList<>();
        backtrack(queens, n, 0, 0, 0, 0, res);
        return res;
    }

    public void backtrack(int[] queens, int n, int cols, int dia1, int dia2, int row, List<List<String>> res) {
        if (row == n) res.add(generateBoard(queens, n));
        else {
            int avaPos = ((1 << n) - 1) & (~(cols | dia1 | dia2));
            while (avaPos != 0) {
                int curpos = avaPos & (-avaPos);
                avaPos = avaPos & (avaPos - 1);
                int col = Integer.bitCount(curpos - 1);
                queens[row] = col;
                backtrack(queens, n, (cols | curpos), (dia1 | curpos) << 1, (dia2 | curpos) >> 1, row + 1, res);
                queens[row] = -1;
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] c = new char[n];
            Arrays.fill(c, '.');
            c[queens[i]] = 'Q';
            list.add(new String(c));
        }
        return list;
    }
}

