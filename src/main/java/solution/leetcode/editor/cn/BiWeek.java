package solution.leetcode.editor.cn;

import java.util.*;

/**
 * @author lumac
 * @since 2020/12/26
 */
public class BiWeek {
    class Solution {
        public int eatenApples(int[] apples, int[] days) {
            PriorityQueue<Apple> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.valid));
            int ans = 0;
            int n = apples.length;
            for (int i = 1; i <= n; i++) {
                pq.add(new Apple(apples[i - 1], i + days[i - 1]));
                while (!pq.isEmpty()) {
                    Apple head = pq.poll();
                    if (head.remain == 0 || head.valid <= i) {
                        continue;
                    }
                    head.remain--;
                    pq.add(head);
                    ans++;
                    break;
                }
            }
            for (int i = n + 1; !pq.isEmpty(); ) {
                while (!pq.isEmpty()) {
                    Apple head = pq.poll();
                    if (head.remain == 0 || head.valid <= i) {
                        continue;
                    }
                    int used = Math.min(head.valid - i, head.remain);
                    i += used;
                    ans += used;
                    break;
                }
            }
            return ans;
        }
    }

    class Apple {
        int remain;
        int valid;

        public Apple(int remain, int valid) {
            this.remain = remain;
            this.valid = valid;
        }
    }

    public static void main(String[] args) {
        int[] a = {9, 10, 1, 7, 0, 2, 1, 4, 1, 7, 0, 11, 0, 11, 0, 0, 9, 11, 11, 2, 0, 5, 5};
        int[] b = {3, 19, 1, 14, 0, 4, 1, 8, 2, 7, 0, 13, 0, 13, 0, 0, 2, 2, 13, 1, 0, 3, 7};
        //int[] a = {3, 1, 1, 0, 0, 2};
        //int[] b = {3, 1, 1, 0, 0, 2};
        //System.out.println(countStudents(a, b));
//        System.out.println(halvesAreAlike("AbCdEfGh"));
        System.out.println(eatenApples(a, b));

    }

    public int[] findBall(int[][] grid) {
        return null;
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        return null;
    }

    public static int eatenApplesaa(int[] apples, int[] days) {
        int[] can = new int[apples.length];
        for (int i = 0; i < apples.length; i++) {
            can[i] = Math.min(apples[i], days[i]);
        }
        int res = 0;
        int left = 0;
        int[] dp = new int[can.length];
        for (int i = 1; i < can.length; i++) {
            dp[i - 1] = 0;
        }
        return res;
    }

    //你打算每天 最多 吃一个苹果来保证营养均衡
    public static int eatenApples(int[] apples, int[] days) {
        int[] can = new int[apples.length];
        for (int i = 0; i < apples.length; i++) {
            can[i] = Math.min(apples[i], days[i]);
        }
        int res = 0;
        int left = 0;
        for (int i = 0; i < can.length; i++) {
            //苹果够吃完的
            int n = can[i];
            if (left > n) {
                res = res + n;
                res = res - (n - i);
                n = 0;
                left--;
            } else if (n > 0) {
                if (left == n) {
                    left = 0;
                }
                res++;
                n--;
            }
            left = Math.max(n, left);
        }
        return res + left;
    }

    public static boolean halvesAreAlike(String s) {
        if (s == null || s.length() == 0) return true;
        int len = s.length();
        int left = 0, right = 0;
        List<Character> dic = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        for (int i = 0; i < len; i++) {
            if (i <= len / 2 - 1 && dic.contains(s.charAt(i))) {
                left++;
            }
            if (i > len / 2 - 1 && dic.contains(s.charAt(i))) {
                right++;
            }
        }
        return left == right;
    }

    public static int countStudents_(int[] students, int[] sandwiches) {
        return 0;
    }

    public static int countStudents(int[] students, int[] sandwiches) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i : students) {
            list.add(i);
        }
        for (int i = 0; i < sandwiches.length; i++) {
            int size = list.size();
            int pos = 0;
            while (pos < size) {
                if (list.get(pos) == sandwiches[i]) {
                    list.remove(pos);
                    for (int j = 0; j < pos; j++) {
                        list.add(list.get(0));
                        list.remove(0);
                    }
                    break;
                }
                pos++;
            }
            if (pos == size) return list.size();
        }
        return list.size();
    }
}
