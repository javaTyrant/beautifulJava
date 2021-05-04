package dynamicproxy.proxy;

/**
 * @author lumac
 * @since 2020/7/8
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("真实对象保存");
    }
}
