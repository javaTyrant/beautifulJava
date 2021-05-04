package netty.dubboretry;

import netty.HashedWheelTimer;
import netty.Timer;

import java.util.concurrent.TimeUnit;

/**
 * FailbackClusterInvoker
 * https://www.javadoop.com/post/HashedWheelTimer
 *
 * @author lumac
 * @since 2020/6/3
 */
public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        app.invoke();
    }

    private volatile Timer failTimer = null;

    public void invoke() {
        try {
            doInvoke();
        } catch (Throwable e) {
            System.out.println("调用 doInvoke 方法失败，5s 后将进入后台的自动重试，异常信息: " + e);
            addFailed(this::doInvoke);
        }
    }

    // 实际的业务实现
    private void doInvoke() {
        // 这里让这个方法故意失败
        throw new RuntimeException("故意抛出异常");
    }

    private void addFailed(Runnable task) {
        // 延迟初始化
        if (failTimer == null) {
            synchronized (this) {
                if (failTimer == null) {
                    failTimer = new HashedWheelTimer();
                }
            }
        }
        RetryTimerTask retryTimerTask = new RetryTimerTask(task, 3, 5);
        try {
            // 5s 后执行第一次重试
            failTimer.newTimeout(retryTimerTask, 5, TimeUnit.SECONDS);
        } catch (Throwable e) {
            System.out.println("提交定时任务失败，exception: " + e);
        }
    }
}
