package current.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2020/6/8
 */
public class Memoizer<A, V> {
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    //还有漏洞
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        //这个方法不是原子的,so
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

}
