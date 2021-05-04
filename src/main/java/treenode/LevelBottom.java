package treenode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

/**
 * @author lumac
 * @since 2020/9/6
 */
public class LevelBottom implements Tree {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //存放当前的一行
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                TreeNode left = cur.left, right = cur.right;
                level.add(cur.val);
                if (left != null) queue.offer(left);
                if (right != null) queue.offer(right);
            }
            res.add(0, level);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(File.separator);
        File file = new File("/tmp/20200808/" + "hello.xlsx");
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
    }

}

