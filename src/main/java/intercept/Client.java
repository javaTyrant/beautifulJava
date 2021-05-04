package intercept;

/**
 * @author lumac
 * @since 2020/7/8
 */
public class Client {
    public static void main(String[] args) {
        Target target = new TargetImpl();
        //加入拦截插件后
        InterceptImpl intercept = new InterceptImpl();
        target = (Target) intercept.register(target);
        target.execute();
        System.out.println("第二个方法开始执行啦......");
        target.execute2();
    }
}
