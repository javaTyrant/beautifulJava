package strings;

import java.util.Arrays;
import java.util.List;

/**
 * @author lumac
 * @since 2020-05-23
 */
public class ReverseString {
    public static int maxVowels(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        List<Character> list = Arrays.asList('a', 'e', 'i', 'o', 'u');
        int res = 0;
        for (int i = 0; i <= s.toCharArray().length - k; i++) {
            int end = i + k;
            int temp = 0;
            for (int j = i; j < end; j++) {
                if (list.contains(s.charAt(j))) {
                    temp++;
                }
            }
            if (temp > res) {
                res = temp;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(maxVowels("rhythms", 4));
//        System.out.println(maxVowels("abciiidef", 3));
        System.out.println(maxVowels("weallloveyou", 7));
        reverseWords("hello wode   jack");
    }

    public static String reverseWords(String s) {
        String[] strs = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].equals("")) {
                continue;
            }
            sb.append(strs[i]);
            if (i > 0)
                sb.append(" ");
        }
        return sb.toString();
    }

    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) {
                return i;
            }
        }
        return -1;
    }
}
