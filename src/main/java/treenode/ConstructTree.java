package treenode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2020/7/27
 */
public class ConstructTree implements Tree {
    private Map<Integer, Integer> indexMap;

    /**
     * 变量太多,要理清楚关系
     *
     * @param preorder      前序遍历数组
     * @param preorderLeft  左
     * @param preorderRight 右
     * @param inorderLeft   中序遍历的左值
     * @return
     */
    public TreeNode myBuildTree(int[] preorder, int preorderLeft, int preorderRight, int inorderLeft) {
        if (preorderLeft > preorderRight) {
            return null;
        }
        int curRoot = preorder[preorderLeft];
        //获取根节点的索引位置,刚开始用前序遍历的第一个节点,定位到中序遍历的根节点位置
        int inorderRootPosition = indexMap.get(curRoot);
        //构造当前的root
        TreeNode root = new TreeNode(curRoot);
        //左树的大小,根节点的索引
        int leftSubtreeSize = inorderRootPosition - inorderLeft;
        //
        root.left = myBuildTree(preorder, preorderLeft + 1, preorderLeft + leftSubtreeSize, inorderLeft);
        //
        root.right = myBuildTree(preorder, preorderLeft + leftSubtreeSize + 1, preorderRight, inorderRootPosition + 1);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点,我们把中序遍历先放入map,前序遍历的第一个节点肯定是根节点
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, n - 1, 0);
    }

    public static void main(String[] args) {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        ConstructTree tree = new ConstructTree();
        TreeNode treeNode = tree.buildTree(pre, in);
        System.out.println(treeNode);
    }
}
