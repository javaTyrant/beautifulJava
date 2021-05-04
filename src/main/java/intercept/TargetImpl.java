package intercept;

/**
 * @author lumac
 * @since 2020/7/8
 */
public class TargetImpl implements Target {
    @Override
    public void execute() {
        System.out.println("执行啦");
    }

    @Override
    public void execute2() {
        System.out.println("不要被拦截的方法");
    }
}
