package solution.leetcode.editor.cn;

//最小栈
public class LeetCode155 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class MinStack {
        class Node {
            private int min;
            private int val;
            private Node next;

            public Node(int val, int min, Node node) {
                this.min = min;
                this.next = node;
                this.val = val;
            }

            public Node(int min, int val) {
                this.min = min;
                this.val = val;
            }
        }

        private Node node;

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            if (node == null) {
                node = new Node(x, x);
            } else {
                node = new Node(x, Math.min(x, node.min), node);
            }
        }

        public void pop() {
            node = node.next;
        }

        public int top() {
            return node.val;
        }

        public int getMin() {
            return node.min;
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
