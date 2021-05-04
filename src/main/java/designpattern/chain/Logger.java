package designpattern.chain;

/**
 * @author lumac
 * @since 2020/6/24
 */
public abstract class Logger {
    //可以抽成枚举类
    public static int ERR = 3;
    public static int NOTICE = 5;
    public static int DEBUG = 7;
    protected int mask;

    // The next element in the chain of responsibility
    protected Logger next;

    public Logger setNext(Logger l) {
        next = l;
        return this;
    }

    public final void message(String msg, int priority) {
        if (priority <= mask) {
            writeMessage(msg);
            if (next != null) {
                next.message(msg, priority);
            }
        }
    }

    //模板方法
    protected abstract void writeMessage(String msg);
}
