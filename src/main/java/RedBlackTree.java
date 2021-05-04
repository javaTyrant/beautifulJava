import java.util.LinkedList;

/**
 * @author lumac
 * @since 2020/11/11
 */
public class RedBlackTree {
    public static void main(String[] args) {
        test(2);
        test(3);
        test(4);
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        System.out.println(list);

    }

    private static void test(int n) {
        System.out.println((n << 1) - (n >>> 1));
    }
}
