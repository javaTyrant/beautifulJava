package treenode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lumac
 * @since 9/24/20
 */
public class FindMostTree implements Tree {
    //
    int base, count, maxCount;
    List<Integer> answer = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        TreeNode cur = root, pre;
        while (cur != null) {
            if (cur.left == null) {
                update(cur.val);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            if (pre.right == null) {
                pre.right = cur;
                cur = cur.left;
            } else {
                pre.right = null;
                update(cur.val);
                cur = cur.right;
            }
        }
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    public void update(int x) {
        if (x == base) {
            ++count;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answer.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(base);
        }
    }

    private static int round(int a, double b) {
        return (int) Math.ceil(a / b);
    }

    public static void main(String[] args) {
        FindMostTree tree = new FindMostTree();
        Integer[] nums = {1, null, 2, 2};
        TreeNode nodes = tree.of(nums);
        System.out.println(Arrays.toString(tree.findMode(nodes)));
        System.out.println(round(9,9 ));
    }
}
