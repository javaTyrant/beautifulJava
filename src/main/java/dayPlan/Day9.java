package dayPlan;

import linklist.Link;
import treenode.Tree;

import java.util.*;

/**
 * @author lumac
 * @since 2021/3/4
 */
public class Day9 implements Tree, Link {
    public static void main(String[] args) {
        Day9 day9 = new Day9();
        int[] arr1 = {1, 1, 1, 1, 1, 4, 5};
        //System.out.println(Arrays.toString(day9.maxSlidingWindow(arr, 3)));
        System.out.println(Arrays.toString(day9.maxSlidingWindow(arr1, 6)));
        System.out.println(day9.lengthOfLongestSubstring("abba"));
        System.out.println(day9.myPow(2, -2));
        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(day9.minPathSum(grid));
        int[] arr2 = {2, 1, 3, 4, 5, -1};
        //System.out.println(day9.partition(arr2, 0, arr2.length - 1));
        day9.sort(arr2);
        System.out.println(Arrays.toString(arr2));
        System.out.println(Integer.toBinaryString(8));
        System.out.println(Integer.toBinaryString(-8));
        System.out.println(Integer.toBinaryString(8 & (-8)));
        int[] arr3 = {1, 3, 5, 7, 9};
        int[] arr6 = {1, 2, 3, 4, 5};
        int[] arr4 = {2, 4, 6, 8, 10};
        int[] arr5 = {12};
        ListNode listNode = day9.arrayToListNode(arr3);
        ListNode listNode2 = day9.arrayToListNode(arr4);
        ListNode listNode3 = day9.arrayToListNode(arr5);
        ListNode listNode6 = day9.arrayToListNode(arr6);
        ListNode[] listNodes = {listNode, listNode2, listNode3};
        day9.printNode(day9.mergeKLists(listNodes));
        day9.printNode(day9.rotateRight(listNode6, 2));
    }

    //121. 买卖股票的最佳时机:买卖一次,选最低的买,最高的卖
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        //
        for (int p : prices) {
            //保存最低的
            min = Math.min(min, p);
            //保存最大的
            max = Math.max(max, p - min);
        }
        return max;
    }

    //122. 买卖股票的最佳时机 II:尽可能多的完成交易
    public int maxProfit2(int[] prices) {
        int max = 0;
        //从1开始计算
        for (int i = 1; i < prices.length; i++) {
            //如果当前的大于前一天的
            if (prices[i] > prices[i - 1]) {
                //就累加,贪婪
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }

    //旋转链表
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        //需要两个指针
        ListNode cursor = head;
        ListNode tail;//尾指针
        int length = 1;
        //循环 得到总长度
        while (cursor.next != null) {
            cursor = cursor.next;
            length++;
        }
        //得到循环的次数
        int loop = length - (k % length);
        //尾节点指向cursor
        tail = cursor;
        //改成循环链表
        cursor.next = head;
        //指向头结点
        cursor = head;
        //开始循环
        for (int i = 0; i < loop; i++) {
            cursor = cursor.next;
            tail = tail.next;
        }
        tail.next = null;//改成单链表
        return cursor;//返回当前头
    }

    //148. 排序链表
    public ListNode sortList(ListNode head) {
        //base case不能忘
        if (head == null || head.next == null)
            return head;

        ListNode pre = null, slow = head, fast = head;
        //找到中点
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //切断
        pre.next = null;
        //归并
        return merge(sortList(head), sortList(slow));
    }

    //合并两个有序链表
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
            if (l1 != null) cur.next = l1;
            if (l2 != null) cur.next = l2;
        }
        return dummy.next;
    }

    //215. 数组中的第K个最大元素
    public static int findKthLargest(int[] nums, int k) {
        //小顶堆
        Queue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            //小于add
            if (pq.size() < k) {
                pq.add(num);
                //pq.size() >= k.remove掉队里最小的.
            } else if (pq.peek() < num) {
                pq.poll();
                pq.add(num);
            }
        }
        //取堆顶就是第k大元素
        return pq.peek();
    }

    //23. 合并K个升序链表:时间复杂度KnLogk.
    public ListNode mergeKLists(ListNode[] lists) {
        //优先队列:小顶堆,以第一个元素为基准
        PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
        //根据首节点放入优先队列中:
        for (ListNode node : lists) {
            if (node != null) {
                q.add(node);
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!q.isEmpty()) {
            //先取出最小的
            tail.next = q.poll();
            //set一个
            tail = tail.next;
            if (tail.next != null) {
                //不为空再放回去
                q.add(tail.next);
            }
        }
        return head.next;
    }

    //青蛙跳台阶,爬楼梯
    public int climbStairs(int n) {
        int a = 1;
        int b = 1;
        for (int i = 1; i < n; i++) {
            //temp = 1
            int temp = b;
            //1+=0
            b += a;
            //0=tmp
            a = temp;
        }
        return b;
    }

    //最大子序和
    public int maxSubArray(int[] nums) {
        //res = min
        int res = Integer.MIN_VALUE;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            //max
            max = Math.max(max, 0) + nums[i];
            //res
            res = Math.max(res, max);
        }
        return res;
    }

    //8. 字符串转换整数 (atoi)
    public int myAtoi(String s) {
        //先trim
        s = s.trim();
        //长度判断
        if (s.length() == 0) return 0;
        //判断第一个字符
        char first = s.charAt(0);
        if (!Character.isDigit(first) && first != '-' && first != '+') return 0;
        //是否是负数
        boolean neg = first == '-';
        //i从哪开始
        int i = Character.isDigit(first) ? 0 : 1;
        //保存结果
        int res;
        //防止溢出
        long ans = 0L;
        //不越界且是数字
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            //*10 + 当前的字符串,且i要++
            ans = ans * 10 + (s.charAt(i++) - '0');
            //正数溢出
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            //负数溢出,注意是-ans,ans是拼接的正数
            if (neg && -ans < Integer.MIN_VALUE) {
                ans = Integer.MIN_VALUE;
                break;
            }
        }
        //强转成res
        res = (int) ans;
        //正负判断
        return neg ? -res : res;
    }

    //141. 环形链表 快慢指针
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        //快的走两步,慢的走一步
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    //160. 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //都为null返回null
        if (headA == null || headB == null) return null;
        //指针
        ListNode a = headA, b = headB;
        //a不等于b
        while (a != b) {
            //a为空从b再开始走
            a = a == null ? headB : a.next;
            //b为空从a开始走
            b = b == null ? headA : b.next;
        }
        //一定相遇了
        return a;
    }

    //230. 二叉搜索树中第K小的元素
    public int kthSmallest(TreeNode root, int k) {
        //用栈
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (; ; ) {
            //先把小的全部加进去
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            //romove
            root = stack.removeLast();
            //--直到k为止
            if (--k == 0) return root.val;
            //==root.right
            root = root.right;
        }
    }

    //多数元素
    public int majorityElement(int[] nums) {
        int count = 0;
        //
        Integer candidate = null;

        for (int num : nums) {
            //count == 0.跟新candidate
            if (count == 0) {
                candidate = num;
            }
            //num == candidate + 1 否则减1
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    //2的幂
    public boolean isPowerOfTwo(int n) {
        return (n > 0) && (n & -n) == n;
    }

    //三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //for循环
        for (int i = 0; i < nums.length; i++) {
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //left
            int left = i + 1;
            //right
            int right = nums.length - 1;
            //双指针
            while (left < right) {
                //r
                int r = nums[i] + nums[left] + nums[right];
                //
                if (r == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //去重
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (r > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    //72. 编辑距离
    public int minDistance(String word1, String word2) {
        int i = word1.length();
        int j = word2.length();
        if (i * j == 0) return i + j;
        //多放一个位置
        int[][] dp = new int[i + 1][j + 1];
        //处理边界
        for (int k = 0; k < i + 1; k++) {
            dp[k][0] = k;
        }
        //处理边界
        for (int k = 0; k < j + 1; k++) {
            dp[0][k] = k;
        }
        for (int row = 1; row < i + 1; row++) {
            for (int col = 1; col < j + 1; col++) {
                //left
                int left = dp[row - 1][col] + 1;
                //down
                int down = dp[row][col - 1] + 1;
                //left down
                int leftDown = dp[row - 1][col - 1];
                //不等的话 leftDown+1
                if (word1.charAt(row - 1) != word2.charAt(col - 1)) {
                    leftDown += 1;
                }
                //选最小
                dp[row][col] = Math.min(left, Math.min(down, leftDown));
            }
        }
        return dp[i][j];
    }

    //最小路径和:可以原地修改吗?grid的范围呢?
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        //行设值
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    //50. Pow(x, n)
    public double myPow(double x, int n) {
        double res = 1;
        for (int i = n; i != 0; i /= 2) {
            if ((i & 1) != 0) {
                res *= x;
            }
            x = x * x;
        }
        return n < 0 ? 1 / res : res;
    }

    //合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //三个指针
        int p = m + n - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        //都大于0时
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = (nums2[p2] > nums1[p1]) ? nums2[p2--] : nums1[p1--];
        }
        //如果p2还大于0.继续处理
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }

    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //dummy
        ListNode newNode = new ListNode(-1);
        //cur
        ListNode current = newNode;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            //
            current = current.next;
        }
        //
        current.next = l1 == null ? l2 : l1;
        return newNode.next;
    }

    //删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //需要一个dummy
        ListNode dummy = new ListNode(-1);
        //指向head
        dummy.next = head;
        //指针是指向dummy的
        ListNode pre = dummy;
        ListNode cur = dummy;
        while (n >= 0 && cur != null) {
            cur = cur.next;
            n--;
        }
        while (cur != null) {
            cur = cur.next;
            pre = pre.next;
        }
        //pre走到要删除的前一步
        pre.next = pre.next.next;
        return dummy.next;
    }

    //5. 最长回文子串
    public String longestPalindrome(String s) {
        //left right 记录回文的起始和结束位置
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            //取最大
            int max = Math.max(expand(s, i, i), expand(s, i, i + 1));
            //小于则更新
            if (right - left < max) {
                left = i - (max - 1) / 2;
                right = i + max / 2;
            }
        }
        //right要+1
        return s.substring(left, right + 1);
    }

    private int expand(String s, int left, int right) {
        //往两边扩展
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        //right - left - 1
        return right - left - 1;
    }

    //3. 无重复字符的最长子串 O(n)
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int ans = 0;
        //start,end 记录
        for (int start = 0, end = 0; end < s.length(); end++) {
            //获取end
            char c = s.charAt(end);
            if (window.containsKey(c)) {
                //start要取最大:abba
                start = Math.max(start, window.get(c));
            }
            //ans取最大
            ans = Math.max(ans, end - start + 1);
            //放入下一个位置
            window.put(c, end + 1);
        }
        return ans;
    }

    //102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        //队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //初始化level
        int level = 0;
        while (!queue.isEmpty()) {
            res.add(new ArrayList<>());
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                //get level 再add
                res.get(level).add(cur.val);
                //先左后右
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            //level++
            level++;
        }
        return res;
    }

    //239. 滑动窗口最大值:双端队列的head 和 tail都维护了什么信息呢
    public int[] maxSlidingWindow(int[] nums, int k) {
        //构建返回值
        int[] res = new int[nums.length - k + 1];
        //双端队列
        Deque<Integer> deque = new LinkedList<>();
        //
        for (int i = 0; i < nums.length; i++) {
            //队列非空: i - k :什么时候处理first,窗口过期的时候
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                //poll前面的
                deque.pollFirst();
            }
            //此时都是在窗口的范围内的...

            //队列非空 且 队列里的最后小于 当前的.:即什么时候处理last:什么时候删除最后的,队列里小于数组里的
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            //放进去
            deque.offerLast(i);
            //满足存放的条件
            if (i >= k - 1) {
                //
                res[i + 1 - k] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    //93. 复原 IP 地址
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        back(s, ans, new ArrayList<>(), 0);
        return ans;
    }

    //s ans cur pos
    private void back(String s, List<String> ans, List<String> cur, int pos) {
        //ip地址是4个
        if (cur.size() == 4) {
            //长度相等
            if (s.length() == pos) {
                //ans
                ans.add(String.join(".", cur));
            }
            return;
        }
        //ip是三个数字
        for (int i = 1; i <= 3; i++) {
            //越界
            if (pos + i > s.length()) break;
            //截取
            String seg = s.substring(pos, pos + i);
            //base case 以0开始长度不能大于1,长度=3不能大于255
            if (seg.startsWith("0") && seg.length() > 1 || (i == 3 && Integer.parseInt(seg) > 255))
                continue;
            //add
            cur.add(seg);
            //注意是+i 不是加1,
            back(s, ans, cur, pos + i);
            //remove
            cur.remove(cur.size() - 1);
        }
    }

    //354. 俄罗斯套娃信封问题
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public int maxEnvelopes(int[][] envelopes) {
        // sort on increasing in first dimension and decreasing in second
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) {
                return arr2[1] - arr1[1];
            } else {
                return arr1[0] - arr2[0];
            }
        });
        // extract the second dimension and run LIS
        int[] secondDim = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; ++i) secondDim[i] = envelopes[i][1];
        return lengthOfLIS(secondDim);
    }

    private void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int j = partition(arr, l, r);
        quickSort(arr, 0, j - 1);
        quickSort(arr, j + 1, r);
    }

    private int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        while (l < r) {
            while (arr[r] > pivot) r--;
            swap(arr, r, l);
            while (arr[l] < pivot) l++;
            swap(arr, r, l);
        }
        arr[l] = pivot;
        return l;
    }

    //来写个快排吧
    private void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
