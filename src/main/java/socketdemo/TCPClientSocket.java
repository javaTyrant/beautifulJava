package socketdemo;

import java.io.IOException;
import java.net.Socket;

/**
 * @author lumac
 * @since 2020/6/28
 */
public class TCPClientSocket {
    private static Socket socket;

    public static void main(String[] args) {
        int port = 10086;
        String address = "127.0.0.1";
        connectServerSocket(port, address);
        closeSocket();

    }

    private static void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void connectServerSocket(int port, String address) {
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
