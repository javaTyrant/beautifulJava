package netty.current.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lumac
 * @since 2020/6/6
 */
public class CasMain {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        CasCounter casCounter = new CasCounter();
        for (int i = 0; i < 10; i++) {
            service.execute(casCounter::increment);
        }
        System.out.println(casCounter.getValue());
    }
}
