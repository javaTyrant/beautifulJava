package netty;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author lumac
 * @since 2020/6/2
 */
public class App {
    //问题1,为什么run方法里需要timeout的参数,这个timeout是何时构造的,HashedWheelTimeout
    //问题二,方法的执行步骤是什么
    //问题二点1:构造器的步骤
    //问题二点2:newTimeout的步骤
    //谁负责往HashedWheelBucket添加数据:Worker的transferTimeoutsToBuckets
    //谁负责触发Worker的transferTimeoutsToBuckets方法呢,Worker的run

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getProperties());
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());
        Timer timer = new HashedWheelTimer();
        CountDownLatch count = new CountDownLatch(2);
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("hello");
                count.countDown();
            }
        }, 5, TimeUnit.SECONDS);
        timer.newTimeout(timeout -> {
            System.out.println("hello,again");
            count.countDown();
        }, 8, TimeUnit.SECONDS);
        count.await();
        timer.stop();
        System.out.println("what the fuck");


    }
}
