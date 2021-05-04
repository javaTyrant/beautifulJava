package current;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lumac
 * @since 2020-05-26
 */
public class LockWhere {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        StaticTest test1 = new StaticTest();
        StaticTest test2 = new StaticTest();
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            /*
                static方法对class对象加锁,而且
               () -> test1.eat() 可以简写成StaticTest.eat()
               所以new两个对象是不明智的
             */
            service.execute(() -> StaticTest.eat());
            service.execute(() -> StaticTest.say());
        }
        service.shutdown();
    }

    private static void test1() {
        Test test = new Test();
        Test test1 = new Test();
        ExecutorService service = Executors.newFixedThreadPool(5);
        /*
           如果是同一个对象,访问一个对象的两个加锁方法,只有锁释放了,才能执行下一个方法
           如果是不同的对象,这个开启了五个线程,会执行到say的,然后方法全部阻塞在那边等待eat执行
           编译后monitorenter,monitorexit
         */
        for (int i = 0; i < 100; i++) {
            //eat长时间睡眠,say会阻塞吗
            service.execute(test::eat);
            service.execute(test1::say);
        }
        service.shutdown();
    }
}
