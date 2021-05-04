package netty.dubboretry;

import netty.Timeout;
import netty.Timer;
import netty.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @author lumac
 * @since 2020/6/3
 */
public class RetryTimerTask implements TimerTask {
    // 每隔几秒执行一次
    private final long tick;

    // 最大重试次数
    private final int retries;

    private int retryTimes = 0;

    private Runnable task;

    public RetryTimerTask(Runnable task, long tick, int retries) {
        this.tick = tick;
        this.retries = retries;
        this.task = task;
    }

    @Override
    public void run(Timeout timeout) {
        try {
            task.run();
        } catch (Throwable e) {
            if ((++retryTimes) >= retries) {
                // 重试次数超过了设置的值
                System.out.println("失败重试次数超过阈值: ，不再重试" + retries);
            } else {
                System.out.println("重试失败，继续重试");
                rePut(timeout);
            }
        }
    }

    // 通过 timeout 拿到 timer 实例，重新提交一个定时任务
    private void rePut(Timeout timeout) {
        if (timeout == null) {
            return;
        }
        Timer timer = timeout.timer();
        if (timeout.isCancelled()) {
            return;
        }
        timer.newTimeout(timeout.task(), tick, TimeUnit.SECONDS);
    }
}
