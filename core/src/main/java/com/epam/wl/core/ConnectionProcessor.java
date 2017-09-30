package com.epam.wl.core;

import lombok.SneakyThrows;

import java.io.OutputStream;
import java.net.Socket;

class ConnectionProcessor implements Runnable {
    private Socket s;
    private OutputStream os;

    @SneakyThrows
    ConnectionProcessor(Socket socket) {
        s = socket;
        os = socket.getOutputStream();
    }

    @SneakyThrows
    public void run() {
        String body = "Hello World!";
        String response = String.format("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: %d\r\n" +
                "Connection: close\r\n\r\n" +
                "%s", body.length(), body);
        os.write(response.getBytes());
        os.flush();
        s.close();
    }

}
