package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static java.nio.channels.SelectionKey.OP_READ;

/**
 * nio做一个回声服务器
 *
 * @author lumac
 * @since 2020/8/24
 */
public class NioEchoServer {
    private static final int BUF_SIZE = 256;
    private static final int TIMEOUT = 3000;

    public static void main(String args[]) throws Exception {
        // 打开服务端 Socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 打开 Selector
        Selector selector = Selector.open();

        // 服务端 Socket 监听8080端口, 并配置为非阻塞模式
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        // 将channel注册到selector中.
        // 通常我们都是先注册一个OP_ACCEPT事件,然后在OP_ACCEPT到来时,再将这个Channel的OP_READ
        // 注册到Selector中.
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 通过调用 select 方法, 阻塞地等待 channel I/O 可操作
            if (selector.select(TIMEOUT) == 0) {
                System.out.print(".");
                continue;
            }

            //获取I/O操作就绪的SelectionKey,通过SelectionKey可以知道哪些Channel的哪类I/O操作已经就绪.
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                //当获取一个SelectionKey后,就要将它删除,表示我们已经对这个IO事件进行了处理.
                keyIterator.remove();
                if (key.isAcceptable()) {
                    //当OP_ACCEPT事件到来时,我们就有从ServerSocketChannel中获取一个SocketChannel,
                    //代表客户端的连接
                    //注意,在OP_ACCEPT事件中,从key.channel()返回的Channel是ServerSocketChannel.
                    //而在OP_WRITE和OP_READ中,从key.channel()返回的是SocketChannel.
                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                    clientChannel.configureBlocking(false);
                    //在OP_ACCEPT到来时,再将这个Channel的OP_READ注册到Selector中.
                    //注意,这里我们如果没有设置OP_READ的话,即interest set仍然是OP_CONNECT的话,那么select方法会一直直接返回.
                    clientChannel.register(key.selector(), OP_READ, ByteBuffer.allocate(BUF_SIZE));
                }
                if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buf = (ByteBuffer) key.attachment();
                    long bytesRead = clientChannel.read(buf);
                    if (bytesRead == -1) {
                        clientChannel.close();
                    } else if (bytesRead > 0) {
                        key.interestOps(OP_READ | SelectionKey.OP_WRITE);
                        System.out.println("Get data length: " + bytesRead);
                    }
                }

                if (key.isValid() && key.isWritable()) {
                    ByteBuffer buf = (ByteBuffer) key.attachment();
                    //转换成写模式
                    buf.flip();
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    //会写过去
                    clientChannel.write(buf);
                    //clientChannel.write(ByteBuffer.wrap("fuck you son".getBytes()));
                    //write之后buf就没值了
                    if (!buf.hasRemaining()) {
                        //如果写完了,就把read赋值进去
                        key.interestOps(OP_READ);
                    }
                    buf.compact();
                }
            }
        }
    }
}
