package io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lumac
 * @since 2020/8/2
 */
public class Server {


    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            while (true) {
                System.out.println("等待监听");
                Socket s = ss.accept();
                System.out.println("客户端:" + s.getInetAddress().getLocalHost() + "已连接到服务器");
                //
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //读取客户端发送来的消息
                String mess;
                while ((mess = br.readLine()) != null) {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    bw.write(mess + "\n");
                    bw.flush();
                    System.out.println("客户端：" + mess);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
