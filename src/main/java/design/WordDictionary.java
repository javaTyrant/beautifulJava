package design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lumac
 * @since 2020/11/7
 */
public class WordDictionary {
    //根据字符串长度分开存放
    Map<Integer, Set<String>> map = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {

    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        int length = word.length();
        if (map.get(length) != null) {
            map.get(length).add(word);
        } else {
            Set<String> set = new HashSet<>();
            set.add(word);
            map.put(length, set);
        }
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        Set<String> set = map.get(word.length());
        if (set == null) {  //不存在该长度的字符串，直接返回false；
            return false;
        }
        if (set.contains(word)) return true;
        char[] chars = word.toCharArray();
        P:
        for (String s : set) {
            if (word.length() != s.length()) {
                continue;
            }
            char[] cs = s.toCharArray();
            for (int i = 0; i < cs.length; i++) {//逐个字符对比
                if (chars[i] != '.' && chars[i] != cs[i]) {
                    continue P;
                }
            }
            set.add(word);
            return true;
        }
        return false;
    }
}
