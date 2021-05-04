package designpattern.singleton;

/**
 * @author lumac
 * @since 2021/3/3
 */
public class Mythread extends Thread {
    @Override
    public void run() {
        System.out.println(MySingletonLazy.getInstance().hashCode());
    }

    public static void main(String[] args) {

        Mythread[] mts = new Mythread[100];
        for (int i = 0; i < mts.length; i++) {
            mts[i] = new Mythread();
        }

        for (int j = 0; j < mts.length; j++) {
            mts[j].start();
        }
    }
}
