package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    while (!(str = in.readLine()).isEmpty()) {
                        switch (str) {
                            case ("Exit") -> server.close();
                            case ("Hello") -> out.write("Hello kitty\r\n\r\n".getBytes());
                            default -> out.write("What\r\n\r\n".getBytes());
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}