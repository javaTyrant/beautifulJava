package safeRecursion;

/**
 * @author lumac
 * @since 2020-05-13
 */
public class Demo {
    static int addRec(int x, int y) {
        return y == 0 ? x : addRec(++x, --y);
    }

    static TailCall<Integer> addSafe(int x, int y) {
        return y == 0 ? TailCall.ret(x) :
                TailCall.sus(() -> addSafe(x + 1, y - 1));
    }

    public static void main(String[] args) {
        System.out.println(addRec(100000, 10));
        //栈溢出了
//        System.out.println(addRec(10, 100000));
        System.out.println(addSafe(10, 1000000).eval());
    }
}
