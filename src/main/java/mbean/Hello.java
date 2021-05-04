package mbean;

/**
 * @author lumac
 * @since 2020/6/28
 */
public class Hello implements HelloMBean {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printHello() {
        System.out.println("Hello world, " + name);
    }

    @Override
    public void printHello(String whoName) {
        System.out.println("Hello, " + whoName);
    }
}
