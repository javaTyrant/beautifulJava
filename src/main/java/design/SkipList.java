package design;

import java.util.ArrayList;

/**
 * @author lumac
 * @since 2020-05-12
 */
public class SkipList {
    /*
        1---------------------------->7
        1-------> 3-------> 5-------> 7
        1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
     */
    //几率
    private static final double PROBABILITY = 0.5;
    //内部结构
    private final Node head;
    //最大的层数
    private int maxLevel;

    public static class Node {
        //值
        private final Integer key;
        /**
         * 纵向数据结构
         */
        private final ArrayList<Node> nextNodes;

        public Node(Integer key) {
            this.key = key;
            nextNodes = new ArrayList<>();
        }
    }

    public SkipList() {
        // 头结点设置为系统最小
        head = new Node(Integer.MIN_VALUE);
        // 尾结点设置为系统最大
        head.nextNodes.add(new Node(Integer.MAX_VALUE));
        maxLevel = 0;
    }

    /*
     1---------------------------->7
     1-------> 3-------> 5-------> 7
     1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
     search(7)
     maxLevel = 3

     1
    */
    public boolean search(int target) {
        //层数
        int curLevel = maxLevel;
        //头结点指针
        Node curNode = head;
        //果然是二分法哈哈哈,误会了这个算法
        while (curLevel >= 0) {
            //目标元素等于,直接返回,取最上层的元素
            if (target == curNode.nextNodes.get(curLevel).key) {
                return true;
            }
            //目标元素小于
            //思考下如何实现二分查找的
            if (target < curNode.nextNodes.get(curLevel).key) {
                //去下一层寻找
                System.out.println(curNode.nextNodes.get(curLevel).key);
                curLevel--;
            } else {//target >= curNodes.get(curLevel).key
                //否则 指针到
                curNode = curNode.nextNodes.get(curLevel);
                System.out.println(curNode.nextNodes.get(curLevel).key);
            }
        }
        return false;
    }

    public void add(int num) {
        int level = 0;
        while (Math.random() < PROBABILITY) {
            level++;
        }
        while (maxLevel < level) {
            head.nextNodes.add(new Node(Integer.MAX_VALUE));
            maxLevel++;
        }
        Node newNode = new Node(num);
        //指针
        Node cur = head;
        int curLevel = maxLevel;
        while (curLevel >= 0) {
            //
            if (num <= cur.nextNodes.get(curLevel).key) {
                if (curLevel <= level) {
                    newNode.nextNodes.add(0, cur.nextNodes.get(curLevel));
                    cur.nextNodes.set(curLevel, newNode);
                }
                curLevel--;
            } else {
                cur = cur.nextNodes.get(curLevel);
            }
        }
    }

    public boolean erase(int num) {
        Node cur = head;
        int curLevel = maxLevel;
        boolean flag = false;
        while (curLevel >= 0 && cur.nextNodes != null) {
            if (num < cur.nextNodes.get(curLevel).key) {
                curLevel--;
            } else if (num > cur.nextNodes.get(curLevel).key) {
                cur = cur.nextNodes.get(curLevel);
            } else {
                cur.nextNodes.set(curLevel, cur.nextNodes.get(curLevel).nextNodes.get(curLevel));
                curLevel--;
                flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        SkipList list = new SkipList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list.search(30));
    }
}
