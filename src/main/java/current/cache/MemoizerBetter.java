package current.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author lumac
 * @since 2020/6/8
 */
public class MemoizerBetter<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public MemoizerBetter(Computable<A, V> c) {
        this.c = c;
    }

    //还有漏洞
    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        //if块是非原子性的
        if (f == null) {
            Callable<V> eval = () -> c.compute(arg);
            FutureTask<V> ft = new FutureTask<>(eval);
            //cache.put(arg, ft);
            cache.putIfAbsent(arg, f);
            ft.run();
        }
        try {
            return f.get();
        } catch (ExecutionException ignore) {
            throw new NullPointerException();
        }
    }
}
