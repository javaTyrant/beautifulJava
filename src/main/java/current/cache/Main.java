package current.cache;

import org.checkerframework.checker.units.qual.C;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lumac
 * @since 2020/6/8
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        long l = System.nanoTime();
        ExecutorService service = Executors.newFixedThreadPool(10);
        ExpensiveFunction function = new ExpensiveFunction();
        CountDownLatch count = new CountDownLatch(10);
        MemorizerWithHashMap<String, BigInteger> cache = new MemorizerWithHashMap<>(function);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                try {
                    cache.compute("11133338932893");
                    count.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        count.await();
        long e = System.nanoTime();
        System.out.println(e - l);
    }
}
