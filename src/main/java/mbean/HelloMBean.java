package mbean;

/**
 * @author lumac
 * @since 2020/6/28
 */
public interface HelloMBean {
    String getName();

    void setName(String name);

    void printHello();

    void printHello(String whoName);
}
