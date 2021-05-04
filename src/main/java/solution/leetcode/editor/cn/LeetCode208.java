package solution.leetcode.editor.cn;

public class LeetCode208 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {
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
                //获取索引
                int n = c - 'a';
                //如果为null,new一个节点
                if (tmp.next[n] == null) {
                    tmp.next[n] = new TrieNode();
                }
                //移动指针
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

                tmp = tmp.next[n];
            }
            //移动指针
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
                //移动指针
                tmp = tmp.next[n];
            }
            return true;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
