package backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lumac
 * @since 2020-05-25
 */
public class SplitArrayFibonacciSequence {
    public static void main(String[] args) {
        SplitArrayFibonacciSequence sequence = new SplitArrayFibonacciSequence();
        sequence.splitIntoFibonacci("1,2,3,4,5");
    }
    public List<Integer> splitIntoFibonacci(String S) {
        LinkedList<Integer> res = new LinkedList<>();
        dfs(S, 0, res);
        return res;
    }

    public boolean dfs(String S, int index, List<Integer> lis) {
        if (index == S.length()) {
            return lis.size() > 2;
        }
        for (int i = index + 1; i <= S.length(); i++) {
            String temp = S.substring(index, i);
            //长度大于10,或者Long解析出来大于INT_MAX了就直接break
            if (S.charAt(index) == '0' && i > index + 1 || temp.length() > 10 || Long.valueOf(temp) > Integer.MAX_VALUE) {
                break;
            }
            int str = Integer.valueOf(temp);
            int one = lis.size() >= 2 ? lis.get(lis.size() - 1) : -1;
            int two = lis.size() >= 2 ? lis.get(lis.size() - 2) : -1;
            lis.add(str);
            if ((one == -1 || two == -1 || one + two == str) && dfs(S, i, lis)) {
                return true;
            }
            lis.remove(lis.size() - 1);
        }
        return false;
    }
}
