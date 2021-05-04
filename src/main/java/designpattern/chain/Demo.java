package designpattern.chain;

/**
 * 原始的责任链模式
 *
 * @author lumac
 * @since 2020/6/24
 */
public class Demo {
    public static void main(String[] args) {
        // Build the chain of responsibility
        Logger l = new StdoutLogger(Logger.DEBUG).setNext(
                new EmailLogger(Logger.NOTICE).setNext(
                        new StderrLogger(Logger.ERR)));

        // Handled by StdoutLogger
        l.message("Entering function y.", Logger.DEBUG);

        // Handled by StdoutLogger and EmailLogger
        l.message("Step1 completed.", Logger.NOTICE);

        // Handled by all three loggers
        l.message("An error has occurred.", Logger.ERR);
    }
}
