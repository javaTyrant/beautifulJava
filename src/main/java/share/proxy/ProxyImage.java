package share.proxy;

/**
 * @author lumac
 * @since 2020/7/23
 */
public class ProxyImage implements Image {
    private String filename;
    private Image image;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void displayImage() {
        if (image == null)
            image = new RealImage(filename);
        image.displayImage();
    }
}
