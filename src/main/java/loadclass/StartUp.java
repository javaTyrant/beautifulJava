package loadclass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lumac
 * @since 2020/7/8
 */
public class StartUp {
    public static void main(String[] args) throws ClassNotFoundException {

        int i = 0;

        while (true) {
            MyClassLoader mcl = new MyClassLoader();
            System.out.println(mcl.getParent());
            Class<?> personClass = mcl.findClass("loadclass.Person");

            try {
                Object person = personClass.newInstance();
                Method sayHelloMethod = personClass.getMethod("sayHello");
                sayHelloMethod.invoke(person);
                System.out.println(++i);
            } catch (InstantiationException
                    | IllegalAccessException
                    | SecurityException
                    | NoSuchMethodException
                    | IllegalArgumentException
                    | InvocationTargetException e1) {
                e1.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
