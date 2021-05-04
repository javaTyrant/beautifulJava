package designpattern.chain;

/**
 * @author lumac
 * @since 2020/6/24
 */
public class StdoutLogger extends Logger {
    public StdoutLogger(int mask) {
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        System.out.println("Writting to stdout: " + msg);
    }
}
