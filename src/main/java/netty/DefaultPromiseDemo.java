package netty;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;

import java.util.concurrent.*;

/**
 * @author lumac
 * @since 2020/6/23
 */
public class DefaultPromiseDemo {
    public static void main(String[] args) {
        DefaultPromiseDemo testPromise = new DefaultPromiseDemo();
        Promise<String> promise = testPromise.doSomething("哈哈");
        promise.addListener(future -> System.out.println(promise.get() + ", something is done"));
    }

    private Promise<String> doSomething(String value) {
        NioEventLoopGroup loop = new NioEventLoopGroup();
        DefaultPromise<String> promise = new DefaultPromise<>(loop.next());
        loop.schedule(() -> {
            try {
                Thread.sleep(1000);
                promise.setSuccess("执行成功。" + value);
                return promise;
            } catch (InterruptedException ignored) {
                promise.setFailure(ignored);
            }
            return promise;
        }, 0, TimeUnit.SECONDS);
        return promise;
    }
}
