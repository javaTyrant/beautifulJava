package dynamicproxy;

import dynamicproxy.proxy.IUserDao;
import dynamicproxy.proxy.UserDao;
import dynamicproxy.proxy.UserDaoProxy;
import org.junit.Test;

/**
 * @author lumac
 * @since 2020/7/8
 */
public class Demo {
    public void say() {
        System.out.println("hello");
    }

    @Test
    public void testStaticProxy() {
        //目标对象
        IUserDao target = new UserDao();
        //代理对象
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();
    }
//
//    @Test
//    public void testDynamicProxy() {
//        IUserDao target = new UserDao();
//        System.out.println(target.getClass());  //输出目标对象信息
//        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
//        System.out.println(proxy.getClass());  //输出代理对象信息
//        proxy.save();  //执行代理方法
//    }
}
