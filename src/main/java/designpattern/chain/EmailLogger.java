package designpattern.chain;

/**
 * @author lumac
 * @since 2020/6/24
 */
public class EmailLogger extends Logger {
    public EmailLogger(int mask) {
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        System.out.println("Sending via email: " + msg);
    }
}
