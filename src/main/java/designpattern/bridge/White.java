package designpattern.bridge;

/**
 * @author lumac
 * @since 2021/2/27
 */
public class White implements Color {
    @Override
    public void paint(String shape) {
        System.out.println("白色的" + shape);
    }
}
