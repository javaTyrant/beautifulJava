package solution.leetcode.editor.cn;

//684. 冗余连接
public class LeetCode684 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            int[] pre = new int[n + 1];
            //每个pre节点初始化为自己
            for (int i = 0; i <= n; i++) {
                pre[i] = i;
            }
            for (int[] arr : edges) {
                int root1 = findRoot(arr[0], pre);
                int root2 = findRoot(arr[1], pre);
                //arr边的两个节点arr[0],arr[1]有共同的根节点，说明在一个连通子图中，此时这条边不能加入，否则会形成环，因此这条边需要删去
                if (root1 == root2) {
                    return arr;
                }
                //并集，将root1下所有子节点的根节点设为root2，方便下次寻找根节点
                adjust(arr[0], root2, pre);
            }
            return new int[0];
        }

        //寻找该节点的根节点
        private int findRoot(int num, int[] pre) {
            while (pre[num] != num) {
                num = pre[num];
            }
            return num;
        }

        //并集 + 路径压缩
        private void adjust(int x, int root, int[] pre) {
            while (pre[x] != root) {
                int temp = pre[x];
                pre[x] = root;
                x = temp;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
