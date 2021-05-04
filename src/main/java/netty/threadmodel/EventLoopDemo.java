package netty.threadmodel;

import io.netty.channel.Channel;
import io.netty.channel.local.LocalServerChannel;

import java.util.concurrent.TimeUnit;

/**
 * @author lumac
 * @since 2020/6/19
 */
public class EventLoopDemo {
    public static void main(String[] args) {
        //一个eventLoop将由一个永远都不会改变的Thread驱动
        //同时,任务可以直接提交给eventloop实现
        //可以创建多个eventLoop以优化资源
        //单个eventLoop可以指派服务于多个channel
        Channel channel = new LocalServerChannel();
        channel.eventLoop().schedule(() -> System.out.println("60秒后执行"), 60, TimeUnit.SECONDS);
    }
}
