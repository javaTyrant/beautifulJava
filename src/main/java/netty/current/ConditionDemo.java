package netty.current;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lumac
 * @since 2020/6/4
 */
public class ConditionDemo {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        //在 Thread-0 里面，执行了 Condition 的 await 方法后，ReentrantLock 释放了自己的锁，并挂起当前线程 Thread-0
        //在 Thread-1 里面，执行了 Condition 的 signalAll 方法且执行了 ReentrantLock 的 unlock 方法后，先前挂起的 Thread-0 被唤醒并继续执行
        CountDownLatch count = new CountDownLatch(2);
        thread0(count).start();
        thread1(count).start();
        count.await();
        lock.lock();
        System.out.println("获取到锁了吗");
    }

    private static Thread thread0(CountDownLatch count) {
        return new Thread(() -> {
            lock.lock();
            String name = Thread.currentThread().getName();
            System.out.println(name + "getLock");
            System.out.println(name + "start await");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "pass await");
            //            lock.unlock();
            //System.out.println(name + "unlock");
            count.countDown();
        });
    }

    private static Thread thread1(CountDownLatch count) {
        return new Thread(() -> {
            lock.lock();
            String name = Thread.currentThread().getName();
            System.out.println(name + "getLock");
            System.out.println(name + "start await");
//            condition.signal();
            System.out.println(name + "notify");
//            lock.unlock();
            //System.out.println(name + "unlock");
            count.countDown();
        });
    }
}
