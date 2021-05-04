package current;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lumac
 * @since 2020/10/22
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        System.out.println(LocalDate.now().minusMonths(-1).withDayOfMonth(1));
        ExecutorService service = Executors.newFixedThreadPool(4);
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        service.execute(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
