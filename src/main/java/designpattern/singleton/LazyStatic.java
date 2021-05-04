package designpattern.singleton;

/**
 * @author lumac
 * @since 2021/3/3
 */
public class LazyStatic {
    //静态内部类虽然保证了单例在多线程并发下的线程安全性，但是在遇到序列化对象时，默认的方式运行得到的结果就是多例的。
    //内部类:readResolve
    private static class MySingletonHandler {
        private static LazyStatic instance = new LazyStatic();
    }

    private LazyStatic() {
    }

    public static LazyStatic getInstance() {
        return MySingletonHandler.instance;
    }
}
