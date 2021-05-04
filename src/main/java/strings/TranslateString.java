package strings;

/**
 * @author lumac
 * @since 2020/6/9
 */
public class TranslateString {
    //递归法
    public static int translateNum(int num) {
        if (num <= 9) {
            return 1;
        }
        int ba = num % 100;
        System.out.println(num);
//        System.out.println(ba);
        return (ba <= 9 || ba >= 26)
                ? translateNum(num / 10)
                : translateNum(num / 10) + translateNum(num / 100);
    }

    public int translateNum_(int num) {
        String s = String.valueOf(num);
        int a = 1;
        int b = 1;

        for (int i = 2; i <= s.length(); i++) {
            String res = s.substring(i - 2, i);
            int c = res.compareTo("10") >= 0 && res.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;
        }
        return a;

    }

    public int translateNumDp(int num) {
        String src = String.valueOf(num);
        int p, q = 0, r = 1;
        for (int i = 0; i < src.length(); ++i) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                r += p;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        //1,0,2,4 | 10,2,4|10,24 |1,0,24
        System.out.println(translateNum(121212));
        System.out.println(translateNum(121212));
    }
}
