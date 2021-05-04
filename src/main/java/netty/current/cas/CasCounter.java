package netty.current.cas;

/**
 * @author lumac
 * @since 2020/6/6
 */
public class CasCounter {
    private SimulateCas value = new SimulateCas();

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }
}
