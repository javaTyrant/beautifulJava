package designpattern.bridge;

/**
 * @author lumac
 * @since 2021/2/27
 */
public class Rectangle extends Shape {
    @Override
    public void draw() {
        color.paint("长方形");
    }
}
