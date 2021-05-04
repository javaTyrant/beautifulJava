import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author lumac
 * @since 2020/11/13
 */
public class TestCall {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> a = CompletableFuture.supplyAsync(TestCall::callA);
        CompletableFuture<String> b = CompletableFuture.supplyAsync(TestCall::callB);
        CompletableFuture<String> c = CompletableFuture.supplyAsync(TestCall::callC);
        String aa = a.get(5, TimeUnit.SECONDS);
        String bb = b.get(5, TimeUnit.SECONDS);
        String cc = c.get(5, TimeUnit.SECONDS);
        System.out.println((System.currentTimeMillis() - start) / 1000);
    }

    private static String callA() {
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException ignore) {

        }
        return "A";
    }

    private static String callB() {
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException ignore) {

        }
        return "B";
    }

    private static String callC() {
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException ignore) {

        }
        return "C";
    }

    @Test
    public void testCom() {
        CompletableFuture<String> future = CompletableFuture.completedFuture("message");
        System.out.println(future.isDone());
        System.out.println(future.getNow(null));
    }

    @Test
    public void testAsync() throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            //异步执行通过ForkJoinPool实现， 它使用守护线程去执行任务
            assertTrue(Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        assertFalse(future.isDone());
        TimeUnit.SECONDS.sleep(3);
        assertTrue(future.isDone());
    }

    @Test
    public void testJoin() throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1开始执行");
            });
            thread.start();
            thread.join();
            Thread thread2 = new Thread(() -> {
                System.out.println("线程2开始执行");
            });
            thread2.start();

        }
    }
}