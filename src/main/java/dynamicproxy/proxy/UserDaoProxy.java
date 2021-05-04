package dynamicproxy.proxy;

/**
 * @author lumac
 * @since 2020/7/8
 */
public class UserDaoProxy implements IUserDao {
    //代理类要实现通用的接口,然后通过构造器把需要代理的类传进来
    //然后代理类相同的方法里加扩展点
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开启事务");//扩展了额外功能
        target.save();
        System.out.println("提交事务");
    }
}
