package netty.current;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lumac
 * @since 2020/6/5
 */
public class AcquireLockDemo {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到锁了");
        });
        service.execute(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到锁了");
        });

        service.execute(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到锁了");
        });
        service.execute(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到锁了");
        });
    }
}
