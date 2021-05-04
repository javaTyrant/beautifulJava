package designpattern.bridge;

/**
 * @author lumac
 * @since 2021/2/27
 */
public class Square extends Shape {
    @Override
    public void draw() {
        color.paint("正方形");
    }
}
