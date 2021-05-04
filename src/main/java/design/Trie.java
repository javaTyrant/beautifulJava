package design;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lumac
 * @since 2020/5/27
 */
public class Trie {
    //
    private TrieNode node;

    private class TrieNode {
        private TrieNode[] next;
        private boolean isEnd;

        //构造器勿忘
        public TrieNode() {
            next = new TrieNode[26];
            isEnd = false;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        node = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode tmp = node;
        for (char c : word.toCharArray()) {
            //算出索引的位置
            int n = c - 'a';
            //如果等于空
            if (tmp.next[n] == null) {
                //插入到这个位置
                tmp.next[n] = new TrieNode();
            }
            //索引移动
            tmp = tmp.next[n];
        }
        tmp.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode tmp = node;
        for (char c : word.toCharArray()) {
            int n = c - 'a';
            if (tmp.next[n] == null) return false;
            //索引移动
            tmp = tmp.next[n];
        }
        return tmp.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode tmp = node;
        for (char c : prefix.toCharArray()) {
            int n = c - 'a';
            if (tmp.next[n] == null) return false;
            //索引移动
            tmp = tmp.next[n];
        }
        return true;
    }

    private static int getDecimalPointLen(BigDecimal decimal) {
        BigDecimal subtract = decimal.subtract(BigDecimal.valueOf(decimal.intValue()));
        String s = subtract.toString();
        return (s.contains(".")) ? s.length() - 2 : 0;
    }

    @Data
    static class ProArea {
        private String pro;//省份
        private String are;//地市
        private String pre;//县

        public ProArea(String pro, String are, String pre) {
            this.pro = pro;
            this.are = are;
            this.pre = pre;
        }
    }

    public static void main(String[] args) {
//        Trie trie = new Trie();
//        trie.insert("a");
//        trie.insert("apple");
//        System.out.println(trie.search("app"));
//        System.out.println(trie.startsWith("app"));
//        System.out.println(getDecimalPointLen(null));
//        System.out.println(getDecimalPointLen(BigDecimal.valueOf(0)));
//        System.out.println(getDecimalPointLen(BigDecimal.valueOf(0.1)));
//        System.out.println(getDecimalPointLen(BigDecimal.valueOf(0.11)));
//        System.out.println(getDecimalPointLen(BigDecimal.valueOf(0.111)));
//        System.out.println(getDecimalPointLen(BigDecimal.valueOf(11.111)));
        List<ProArea> infoVOS = Lists.newArrayList();
        ProArea sd = new ProArea("山东", "济南", "县城1");
        ProArea sd1 = new ProArea("山东", "济南", "县城2");
        ProArea yt = new ProArea("山东", "烟台", "县城3");
        ProArea cs = new ProArea("湖南", "长沙", "县城4");
        ProArea xt = new ProArea("湖南", "湘潭", "县城5");
        ProArea xt2 = new ProArea("湖南", "湘潭", "县城6");
        infoVOS.add(sd);
        infoVOS.add(sd1);
        infoVOS.add(yt);
        infoVOS.add(cs);
        infoVOS.add(xt);
        infoVOS.add(xt2);
        Map<String, Map<String, List<ProArea>>> infoMap = infoVOS.stream()
                .collect(Collectors.groupingBy(ProArea::getPro, Collectors.groupingBy(ProArea::getAre)));
        List<ProArea> ll = new ArrayList<>();
        infoMap.forEach((k, v) -> v.forEach((k1, v1) -> ll.addAll(v1)));
        System.out.println(JSON.toJSONString(infoMap));
    }
}
