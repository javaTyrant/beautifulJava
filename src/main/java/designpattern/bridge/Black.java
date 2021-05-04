package designpattern.bridge;

/**
 * @author lumac
 * @since 2021/2/27
 */
public class Black implements Color {
    @Override
    public void paint(String shape) {
        System.out.println("黑色的" + shape);
    }
}
