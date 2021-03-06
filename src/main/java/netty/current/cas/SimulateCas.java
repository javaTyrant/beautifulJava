package netty.current.cas;

/**
 * 模拟cas
 *
 * @author lumac
 * @since 2020/6/6
 */
public class SimulateCas {

    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expect, int newValue) {
        int old = value;
        if (old == expect) {
            value = newValue;
        }
        //之前的值
        return old;
    }

    public synchronized boolean compareAndSet(int expect, int newValue) {
        return (expect == compareAndSwap(expect, newValue));
    }

}
