package current.cache;

/**
 * @author lumac
 * @since 2020/6/8
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
