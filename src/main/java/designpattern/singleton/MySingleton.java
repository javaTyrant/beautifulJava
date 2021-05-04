package designpattern.singleton;

/**
 * @author lumac
 * @since 2021/3/3
 */
public class MySingleton {
    private static MySingleton instance = new MySingleton();

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        return instance;
    }
}
