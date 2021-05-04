package designpattern.bridge;

/**
 * @author lumac
 * @since 2021/2/27
 */
public abstract class Shape {
    //组合color,color是抽象的,就不用每个颜色+形状罗列
    Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
