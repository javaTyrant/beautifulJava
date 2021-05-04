package current;

/**
 * @author lumac
 * @since 2020/11/18
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().isInterrupted());
            System.out.println("hello");
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println(thread.isAlive());
    }
}
