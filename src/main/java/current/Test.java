package current;

/**
 * @author lumac
 * @since 2020-05-26
 */
public class Test {
    public synchronized void say() {
        System.out.println("hello   " + Thread.currentThread().getName());
    }

    public synchronized void eat() {
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("eat   " + Thread.currentThread().getName());
    }
}
