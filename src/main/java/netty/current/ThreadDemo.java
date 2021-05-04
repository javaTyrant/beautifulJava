package netty.current;

/**
 * @author lumac
 * @since 2020/6/6
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread hello = new Thread(() -> {
            try {
                Thread.sleep(100000);
                System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        hello.start();
        //Waits for this thread to die.
        hello.join(10);
        System.out.println("有我快吗");
    }
}
