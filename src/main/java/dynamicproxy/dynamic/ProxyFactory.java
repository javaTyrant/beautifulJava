package dynamicproxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * @author lumac
 * @since 2020/7/8
 */
public class ProxyFactory {
    // 维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 为目标对象生成代理对象
    public Object getProxyInstance() {
        //(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("开启事务");
                    // 执行目标对象方法
                    Object returnValue = method.invoke(target, args);
                    System.out.println("提交事务");
                    System.out.println(returnValue);
                    //the value to return from the method invocation on the proxy instance.
                    return null;
                });
    }
}
