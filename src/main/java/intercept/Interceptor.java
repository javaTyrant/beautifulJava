package intercept;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lumac
 * @since 2020/7/8
 */
public interface Interceptor {
    Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    Object register(Object target);
}
