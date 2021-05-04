package designpattern.chain;

/**
 * @author lumac
 * @since 2020/6/24
 */
public class StderrLogger extends Logger {
    public StderrLogger(int mask) {
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        System.out.println("Sending to stderr: " + msg);
    }
}
