package netty.current;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lumac
 * @since 2020/6/4
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> array = new ArrayBlockingQueue<>(10);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            array.add(1);
            System.out.println("添加了元素");
        });
        service.execute(() -> {
            System.out.println("准备取数据了");
            //take是阻塞的
            try {
                System.out.println(array.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //poll是立马返回
            //System.out.println(array.poll());
            System.out.println("取到数据了");
        });
        service.shutdown();
    }
}
