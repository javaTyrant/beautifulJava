package solution.leetcode.editor.cn;

/**
 * @author lumac
 * @since 2020/12/23
 */
public class LCOF62 {
    //这题的思考过程更重要一些
    public static int lastRemaining(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

    public static int lastRemainingRe(int n, int m) {
        return f(n, m);
    }

    public static int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        //(m+x)%n是如何推导出来的
        System.out.println("(m + x) % n " + m + "+ " + x + "%" + n);
        return (m + x) % n;
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3));
        System.out.println(lastRemainingRe(5, 3));
    }
}
