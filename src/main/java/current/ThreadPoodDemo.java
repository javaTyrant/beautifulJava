package current;

import java.util.concurrent.*;

/**
 * @author lumac
 * @since 2020/6/12
 */
public class ThreadPoodDemo {
    static ExecutorService service = new ThreadPoolExecutor(2, 5,
            10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException {
        //int corePoolSize,
        //                              int maximumPoolSize,
        //                              long keepAliveTime,
        //                              TimeUnit unit,
        //                              BlockingQueue<Runnable> workQueue
//        ExecutorService service = Executors.newFixedThreadPool(5);
        //
        for (int i = 0; i < 7; i++) {
            int finalI = i;
            service.execute(() -> {
                if (Thread.currentThread().getName().contains("main")) {
                    System.out.println();
                }
                System.out.println(Thread.currentThread().getName() + "***" + finalI);
                while (true) {

                }
            });
        }
        Thread.sleep(1000);
        System.out.println("执行到了吗");
    }
}
