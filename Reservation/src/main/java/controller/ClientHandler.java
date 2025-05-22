/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import protocol.Request;
import protocol.Response;


/**
 *
 * @author 00rya
 */
public class ClientHandler implements Runnable {

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private AtomicInteger clientCount;

    public ClientHandler(Socket socket, AtomicInteger clientCount) {
        this.socket = socket;
        this.clientCount = clientCount;
        System.out.println("클라이언트 연결됨, 현재 접속자 수: " + clientCount);

    }

    @Override
public void run() {
    try {
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());

        while (true) {
            Request request = (Request) input.readObject();
            System.out.println("요청 받음: " + request.getCommand());
            Response response;

            switch (request.getCommand()) {
                case "LOGIN":
                    LoginHandler loginHandler = new LoginHandler();
                    response = loginHandler.handle(request);
                    break;
                default:
                    response = new Response(false, "알 수 없는 명령입니다.", null);
            }

            output.writeObject(response);
            output.flush();
        }
    } catch (java.io.EOFException eof) {
        System.out.println("클라이언트가 정상 종료했습니다: " + socket.getInetAddress());
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("클라이언트 연결 중 예외 발생: " + socket.getInetAddress());
        e.printStackTrace();
    } finally {
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (socket != null) socket.close();
        } catch (IOException ignored) {}

        // 연결 종료 시 접속자 수 감소
        int remainingClients = clientCount.decrementAndGet();
        System.out.println("클라이언트 연결 종료, 현재 접속자 수: " + remainingClients);
        }
    }
}