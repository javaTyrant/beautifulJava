package socketdemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lumac
 * @since 2020/6/28
 */
public class TCPServerSocket {
    private static ServerSocket serverSocket;
    private static Socket socket;

    public static void main(String[] args) {
        startServerSocket(10086);
    }

    private static void startServerSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            System.out.println("接受的客户端的IP地址以及端口信息：" + socket.getRemoteSocketAddress());
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
