package treenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author lumac
 * @since 9/29/20
 */
public class TreeTre implements Tree {
    public List<Integer> postorderTraversal(TreeNode root) {
      /*  List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur != null && cur.left != null) {
                stack.push(cur.left);
            } else if (cur != null && cur.right != null) {
                stack.push(cur.right);
            } else {
                if (cur != null) {
                    res.add(cur.val);
                }
            }
        }
        return res;*/
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        TreeTre t = new TreeTre();
        Integer[] i = {1, null, 2, 3};
        TreeNode node = t.of(i);
        System.out.println(t.postorderTraversal(node));
    }
}
