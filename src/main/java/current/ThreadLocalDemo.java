package current;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lumac
 * @since 2020-05-13
 */
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
//        ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            executorService.execute(() -> {
                threadLocal.set(finalI);
                Runnable run = () -> {
                    threadLocal.set(finalI + 2);
                    System.out.println(threadLocal.get() + "子线程");
                };
                Thread thread = new Thread(run);
                thread.start();
                System.out.println(threadLocal.get());
            });
        }
        Thread.sleep(200);
        System.out.println(threadLocal.get());
        executorService.shutdown();
    }
}
