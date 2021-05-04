package netty;

import java.util.concurrent.TimeUnit;

/**
 * @author lumac
 * @since 2020/6/2
 */
public interface TimerTask {
    /**
     * Executed after the delay specified with
     * {@link Timer#newTimeout(TimerTask, long, TimeUnit)}.
     *
     * @param timeout a handle which is associated with this task
     */
    void run(Timeout timeout) throws Exception;
}
