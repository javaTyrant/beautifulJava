package current.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2020/6/8
 */
public class MemorizerWithHashMap<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public MemorizerWithHashMap(Computable<A, V> c) {
        this.c = c;
    }

    //这个问题是对整个方法加锁,有明显的伸缩性问题
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
