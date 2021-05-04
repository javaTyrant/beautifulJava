package dp;

/**
 * @author lumac
 * @since 2020/6/22
 */
public class PatternMatch {
    public static boolean patternMatching(String pattern, String value) {
        int countA = 0, countB = 0;
        for (char ch : pattern.toCharArray()) {
            if (ch == 'a') {
                ++countA;
            } else {
                ++countB;
            }
        }
        if (countA < countB) {
            int temp = countA;
            countA = countB;
            countB = temp;
            char[] array = pattern.toCharArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] == 'a' ? 'b' : 'a';
            }
            pattern = new String(array);
        }
        if (value.length() == 0) {
            return countB == 0;
        }
        if (pattern.length() == 0) {
            return false;
        }
        for (int len_a = 0; countA * len_a <= value.length(); ++len_a) {
            int rest = value.length() - countA * len_a;
            if ((countB == 0 && rest == 0) || (countB != 0 && rest % countB == 0)) {
                int len_b = (countB == 0 ? 0 : rest / countB);
                int pos = 0;
                boolean correct = true;
                String value_a = "", value_b = "";
                for (char ch : pattern.toCharArray()) {
                    if (ch == 'a') {
                        String sub = value.substring(pos, pos + len_a);
                        if (value_a.length() == 0) {
                            value_a = sub;
                        } else if (!value_a.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_a;
                    } else {
                        String sub = value.substring(pos, pos + len_b);
                        if (value_b.length() == 0) {
                            value_b = sub;
                        } else if (!value_b.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_b;
                    }
                }
                if (correct && !value_a.equals(value_b)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(patternMatching("abba", "hicccccchi"));
    }
}
