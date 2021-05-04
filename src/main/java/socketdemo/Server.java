package socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lumac
 * @since 2020/6/28
 */
public class Server {
    public static void main(String[] args) throws IOException {
        while (true) {
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket so = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(so.getInputStream()));
            String s = reader.readLine();
            System.out.println(s);
        }
    }
}
