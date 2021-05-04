package designpattern.singleton;

/**
 * @author lumac
 * @since 2021/3/3
 */
public class MySingletonLazy {
    private static MySingletonLazy instance = null;

    private MySingletonLazy() {
    }

    //方法加锁
    public static MySingletonLazy getInstance() {
        try {
            if (instance != null) {//懒汉式

            } else {
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(3000);
                synchronized (MySingletonLazy.class) {
                    if (instance == null) {
                        instance = new MySingletonLazy();
                    }
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

}
