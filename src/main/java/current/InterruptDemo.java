package current;

/**
 * @author lumac
 * @since 9/29/20
 */
public class InterruptDemo {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        thread.start();
    }
}
