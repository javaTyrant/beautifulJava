package dayPlan;

import java.util.*;

/**
 * @author lumac
 * @since 2021/2/26
 */
public class Day4 {
    public static void main(String[] args) {
        int[] A = {1, 3, 5, 7, 9};
        int[] B = {2, 4, 6, 8, 10};
        Day4 day4 = new Day4();
        day4.findMedianSortedArrays(A, B);
        System.out.println(day4.countAndSay(6));
    }

    //外观数列
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String num = countAndSay(n - 1);
        StringBuilder s = new StringBuilder();
        //
        for (int i = 0; i < num.length(); i++) {
            int count = 1;
            //如果i和i+1个位置相等,count++,i++
            while (i < num.length() - 1 && num.charAt(i) == num.charAt(i + 1)) {
                count++;
                i++;
            }
            s.append(count);
            s.append(num.charAt(i));
        }
        return s.toString();
    }
    //堆排序

    //归并排序
    private static void msort(int[] a) {
        //需要一个辅助数组
        int[] aux = new int[a.length];
        //开始
        sort(a, aux, 0, a.length - 1);
    }

    //
    private static void sort(int[] a, int[] aux, int lo, int hi) {
        //base case
        if (hi <= lo) return;
        //mid
        int mid = lo + (hi - lo) / 2;
        //两次排序
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        //归并
        merge(a, aux, lo, mid, hi);
    }

    // 美妙
    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        //复制到辅助数组
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        //
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            //i用完取j
            if (i > mid) a[k] = aux[j++];
                //j用完取i
            else if (j > hi) a[k] = aux[i++];
                //取小的
            else if (aux[j] < aux[i]) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    //快排
    public static void sort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
    }

    static void qSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int p = partition(arr, low, high);
        qSort(arr, low, p - 1);
        qSort(arr, p + 1, high);
    }

    //分区
    private static int partition(int[] arr, int l, int r) {
        //先找一个pivot
        int temp = arr[l];
        while (l < r) {
            //找到arr[r]小于temp的
            while (l < r && arr[r] >= temp) r--;
            //交换
            if (l < r) arr[l] = arr[r];
            //找到arr[l]大于等于temp的
            while (l < r && arr[l] < temp) l++;
            //交换
            if (l < r) arr[r] = arr[l];
        }
        //l==r的时候退出循环
        arr[l] = temp;
        return l;
    }

    //跳跃游戏1
    public boolean canJump(int[] nums) {
        //长度
        int n = nums.length;
        //可到达的右边的最远的位置
        int rightmost = 0;
        //遍历
        for (int i = 0; i < n; ++i) {
            //如果i小于
            if (i <= rightmost) {
                //不断更新
                rightmost = Math.max(rightmost, i + nums[i]);
                //只要最远距离>n-1就能走到
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    //49. 字母异位词分组:nkLog(k)
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ch = s.toCharArray();
            //排序
            Arrays.sort(ch);
            //key
            String key = String.valueOf(ch);
            //不包含put
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            //否则add s
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    //旋转图像
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先以对角线（左上-右下）为轴进行翻转
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 再对每一行以中点进行翻转
        int mid = n >> 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < mid; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }


    //正则表达式匹配
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                //p的位置是否是*
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {//不是
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    //s,p的i和j是否匹配,.只在这里面处理
    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    //28. 实现 strStr()
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if (n == 0) return 0;
        //记录第一个相等的位置
        int pn = 0;
        //
        while (pn < m - n + 1) {
            //找到hay里面第一个等于needle首字符串位置
            while (pn < m - n + 1 && haystack.charAt(pn) != needle.charAt(0)) pn++;
            //记录最大长度
            int maxLen = 0;
            //双指针,pl记录needle匹配的位置
            int pl = 0;
            while (pl < n && pn < m && haystack.charAt(pn) == needle.charAt(pl)) {
                maxLen++;
                pl++;
                pn++;
            }
            //maxLen == n找到答案
            if (maxLen == n) return pn - n;
            //回溯
            pn = pn - maxLen + 1;
        }
        return -1;
    }

    //全排列
    public List<List<Integer>> permute(int[] nums) {
        //入参转list
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        //res
        List<List<Integer>> res = new ArrayList<>();
        //find
        findPermutes(list, res, 0);
        return res;
    }

    public void findPermutes(List<Integer> nums,
                             List<List<Integer>> res,
                             int index) {
        //base case
        if (index == nums.size()) {
            res.add(new ArrayList<>(nums));
        } else {
            //回溯
            for (int i = index; i < nums.size(); i++) {
                //交换
                Collections.swap(nums, i, index);
                findPermutes(nums, res, index + 1);
                //back
                Collections.swap(nums, i, index);
            }
        }
    }

    //一个vis数组
    boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> perm = new ArrayList<>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            //如果被回溯过来,continue
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }

    //4. 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            return findMedianSortedArrays(B, A);
        }
        //此时 m <= n也就是B更长
        //half+1确保奇数偶数都一样
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            //i j双指针,关系的推导要搞懂.
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            //如果:
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            }
            //如果:
            else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            //
            else { // i is perfect
                int maxLeft;
                //极端值:i == 0
                if (i == 0) {
                    maxLeft = B[j - 1];
                }
                //极端值:j == 0
                else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    //否则去大的k
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                //奇数直接返回
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                //偶数
                int minRight;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
