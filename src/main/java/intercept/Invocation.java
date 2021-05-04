package intercept;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 一次调用
 *
 * @author lumac
 * @since 2020/7/8
 */
public class Invocation {
    private Object target;
    private Method method;
    private Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    //将自己成员变量的操作尽量放到自己内部，不需要Interceptor获得自己的成员变量再去操作它们，
    //除非这样的操作需要Interceptor的其他支持。然而这儿不需要。
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        //方法,类,参数
        return method.invoke(target, args);
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
