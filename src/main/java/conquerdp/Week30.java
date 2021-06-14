package conquerdp;

/**
 * @author lumac
 * @since 2021/5/30
 */
public class Week30 {
    //  int get(string s) {
    //        int res = 0;
    //        for (auto c: s)
    //            res = res * 10 + c - 'a';
    //        return res;
    //    }
    //
    //    bool isSumEqual(string a, string b, string c) {
    //        return get(a) + get(b) == get(c);
    //    }
    public boolean isSumEqual(String a, String b, String c) {
        return get(a) + get(b) == get(c);
    }

    int get(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            res = res * 10 + c - 'a';
        }
        return res;
    }

    public String maxValue(String n, int x) {
        StringBuilder sb = new StringBuilder();
        if (n.charAt(0) == '-') {
            for (int i = 1; i < n.length(); i++) {
                if (n.charAt(i) - 48 > x) {
                    sb.append(n, 0, i).append(x).append(n.substring(i));
                    return new String(sb);
                }
            }
        } else {
            for (int i = 0; i < n.length(); i++) {
                //-48得到数字的表示. 如果当前的位置小于插入的数字
                if (n.charAt(i) - 48 < x) {
                    //插到i的前面.
                    sb.append(n, 0, i).append(x).append(n.substring(i));
                    return new String(sb);
                }
            }
        }
        return n + x;
    }

    public static void main(String[] args) {
        Week30 solution = new Week30();
        System.out.println(solution.maxValue("256", 2));
        System.out.println(solution.maxValue("649", 5));
    }

    //5774. 使用服务器处理任务ife
    public int[] assignTasks(int[] servers, int[] tasks) {
        return null;
    }
}
