package conquerdp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lufengxiang
 * @since 2021/6/19
 **/
public class GetNumber {
    //400. 第 N 位数字
    //第一步，我们得找到 n 属于哪个数位里的索引。比如 n = 5，那 n 就是个位这个数位里的索引；或者 n = 11，那 n 就是十位这个数位里的索引。
    //第二步，确定了 n 属于哪个数位，我们需要进一步定位到 n 具体属于哪个数。比如 n = 11，指的就是 10 这个数。
    //第三步，确定了 n 属于哪个数，我们就需要算出 n 是这个数的第几位，从而得到最终答案。比如 n = 11，指的是 10 这个数的第 1 位（索引从 0 开始），从而最终答案就是 0。
    public static int findNthDigit(int n) {
        int digitType = 1;
        long digitNum = 9;
        //定位到是几位数
        while (n > digitNum * digitType) {
            n -= (int) digitNum * digitType;
            //1-9 10-99 100-999
            digitType++;
            digitNum *= 10;
        }
        //定位到是这些几位数里面的第几个的第几位
        int indexInSubRange = (n - 1) / digitType;
        int indexInNum = (n - 1) % digitType;
        //还原数字
        int num = (int) Math.pow(10, digitType - 1) + indexInSubRange;
        return Integer.parseInt(("" + num).charAt(indexInNum) + "");
    }

    //剑指 Offer 45. 把数组排成最小的数
    public String minNumber(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        StringBuilder res = new StringBuilder();
        for (int num : nums) {
            res.append(num);
        }
        return res.toString();
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int i = left, j = right;
        int pivot = nums[i];//基准值
        while (i < j) {
            while (i < j && comparator(pivot, nums[j])) {
                j--;
            }
            if (i < j) {
                nums[i++] = nums[j];
            }
            while (i < j && comparator(nums[i], pivot)) {
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        //循环结束，i == j
        nums[i] = pivot;
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    private boolean comparator(int a, int b) {
        return compare(a, b) < 0;
    }

    //替换下就可以返回最大了
    //比较ab相连和ba相连的最大值
    int compare(int a, int b) {
        // 这行注掉会有这样的错误
        //[1,2,3,4,5,6,7,8,9,0]
        // wa "1234567890" expect "0123456789"
        if (a == 0 || b == 0) return a - b; //比较0的特殊性
        return add(a, b) - add(b, a);
    }

    //妙,太妙了
    int add(int a, int b) {
        int n = 1, bb = b;
        while (b != 0) {
            n *= 10;
            b /= 10;
        }
        return a * n + bb;
    }

    //把数字翻译成字符串
    public int translateNum(int num) {
        if (num <= 9) return 1;
        int ba = num % 100;
        return (ba <= 9 || ba >= 26)
                ? translateNum(num / 10)
                : translateNum(num / 10) + translateNum(num / 100);
    }

    //礼物的最大价值
    public int maxValue(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[col + 1];
        //
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                dp[j] = grid[i - 1][j - 1] + Math.max(dp[j - 1], dp[j]);
            }
        }
        return dp[col];
    }

    //最长不含重复字符的子字符串
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int ans = 0;
        for (int end = 0, start = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (window.containsKey(c)) {
                start = Math.max(start, window.get(c));
            }
            ans = Math.max(ans, end - start + 1);
            window.put(c, end + 1);
        }
        return ans;
    }

    // 丑数
    public int nthUglyNumber(int n) {
        if (n <= 0)
            return -1;
        int[] dp = new int[n];
        dp[0] = 1;
        int id2 = 0, id3 = 0, id5 = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[id2] * 2, Math.min(dp[id3] * 3, dp[id5] * 5));
            // 这里不用else if的原因是有可能id2(3) * 2 == id3(2) * 3
            // 这种情况两个指针都要后移
            if (dp[id2] * 2 == dp[i])
                id2 += 1;
            if (dp[id3] * 3 == dp[i])
                id3 += 1;
            if (dp[id5] * 5 == dp[i])
                id5 += 1;
        }
        return dp[n - 1];
    }

    // 字符串中第一个只出现一次的字符.简单的hash表
    public char firstUniqChar(String s) {
        int[] count = new int[256];
        char[] chars = s.toCharArray();
        //两次遍历.
        for (char c : chars)
            count[c]++;
        //找到次数是1的.
        for (char c : chars) {
            if (count[c] == 1)
                return c;
        }
        return ' ';
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //两个链表的第一个公共节点.
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = (p1 == null) ? headB : p1.next;
            p2 = (p2 == null) ? headA : p2.next;
        }
        return p1;
    }

    //数组中的逆序对
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 1)
            return 0;
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, copy.length);

        return mergeSort(nums, copy, 0, nums.length - 1);
    }

    public int mergeSort(int[] nums, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = nums[start];
            return 0;
        }
        int middle = (start + end) >> 1;
        int leftcount = mergeSort(copy, nums, start, middle);
        int rightcount = mergeSort(copy, nums, middle + 1, end);
        int count = 0;
        int i = middle, j = end;
        int lastindex = end;
        while (i >= start && j >= middle + 1) {
            if (nums[i] > nums[j]) {
                copy[lastindex--] = nums[i--];
                count += j - middle;
            } else
                copy[lastindex--] = nums[j--];
        }
        while (i >= start)
            copy[lastindex--] = nums[i--];
        while (j >= middle + 1)
            copy[lastindex--] = nums[j--];

        return count + leftcount + rightcount;
    }

    //在排序数组中查找数字 I.统计排序数组中,target出现的次数.很精妙的一题.
    public static int search(int[] nums, int target) {
        //二分法
        int left = 0, right = nums.length - 1;
        //默认结果
        int count = 0;
        //
        while (left < right) {
            //mid
            int mid = (left + right) / 2;
            //如果中间值大于target,放弃右边.
            if (nums[mid] >= target)
                //为什么不mid-1呢.大于等于
                right = mid;
            if (nums[mid] < target)
                //left是第一个不满足的
                left = mid + 1;
        }
        //开始累加.一直找到不满足条件的.
        while (left < nums.length && nums[left++] == target)
            count++;
        return count;
    }

    //401. 二进制手表
    //二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
    //每个 LED 代表一个 0 或 1，最低位在右侧。
    //给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。
    //你可以 按任意顺序 返回答案。
    //其高 4位为小时，低 6位为分钟
    public static List<String> readBinaryWatch(int num) {
        List<String> resList = new ArrayList<>();
        for (int i = 0; i < 12; ++i)
            for (int j = 0; j < 60; ++j)
                // i << 6 | j是什么意思?
                if (Integer.bitCount((i << 6) | j) == num)
                    resList.add(i + ":" + (j > 9 ? "" : "0") + j);
        return resList;
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(197));
        int[] arr = {1, 2, 2, 2, 2, 2, 2, 3};
        System.out.println(search(arr, 2));
        System.out.println(readBinaryWatch(1));
    }

    //0～n-1中缺失的数字[0,1,2,3,4,5,6,7,9]
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] != mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
