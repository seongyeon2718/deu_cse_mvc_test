/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;
import controller.ClientHandler;
/**
 *
 * @author 00rya
 */
public class ServerMain {
    private static AtomicInteger clientCount = new AtomicInteger(0);
    private static final int MAX_CLIENTS = 3;

    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("서버 IP: " + localHost.getHostAddress());

            ServerSocket serverSocket = new ServerSocket(5000, 50, InetAddress.getByName("0.0.0.0"));
            System.out.println("서버 시작됨. 클라이언트 접속 대기 중...");
            
            while (true) {
                Socket socket = serverSocket.accept();

                if (clientCount.get() >= MAX_CLIENTS) {
                    System.out.println("접속 제한 초과! 연결 거부: " + socket.getInetAddress());
                    socket.close();
                    continue;
                }

                System.out.println("클라이언트 연결됨: " + socket.getInetAddress());
                clientCount.incrementAndGet();

                ClientHandler handler = new ClientHandler(socket, clientCount);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}