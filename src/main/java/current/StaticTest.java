package current;

/**
 * @author lumac
 * @since 2020-05-26
 */
public class StaticTest {
    public static synchronized void say() {
        System.out.println("hello   " + Thread.currentThread().getName());
    }

    public static synchronized void eat() {
        System.out.println("eat   " + Thread.currentThread().getName());
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
