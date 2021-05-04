package intercept;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lumac
 * @since 2020/7/8
 */
//还是有很多可以优化的地方
@Intercept("execute2")
public class InterceptImpl implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("插件拦截的方法");
        //拦截后执行目标方法
        return invocation.proceed();
    }

    @Override
    public Object register(Object target) {
        return TargetProxy.bind(target, this);
    }
}
