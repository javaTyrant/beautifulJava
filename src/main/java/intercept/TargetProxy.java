package intercept;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lumac
 * @since 2020/7/8
 */
public class TargetProxy implements InvocationHandler {
    private final Object target;
    private final Interceptor interceptor;

    public TargetProxy(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    public static Object bind(Object target, Interceptor interceptor) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new TargetProxy(target, interceptor));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //问题是,这个拦截的操作是写死了的
        Intercept annotation = this.interceptor.getClass().getAnnotation(Intercept.class);
        if (annotation != null && annotation.value().equals(method.getName())) {
            return interceptor.intercept(new Invocation(target, method, args));
        }
        return method.invoke(this.target, args);
    }
}
