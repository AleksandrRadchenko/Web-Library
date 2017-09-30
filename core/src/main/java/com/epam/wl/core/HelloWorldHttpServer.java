package com.epam.wl.core;

import lombok.SneakyThrows;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloWorldHttpServer {
    private ServerSocket ss;
    private int port = 8080;

    @SneakyThrows
    public static void main(String[] args) {
        HelloWorldHttpServer server = new HelloWorldHttpServer();
        ExecutorService executorService = Executors.newCachedThreadPool();
        server.ss = new ServerSocket(server.port);
        System.out.println("Server started http://localhost:8080");
        while (!Thread.currentThread().isInterrupted()) {
            Socket s = server.ss.accept();
            executorService.execute(new ConnectionProcessor(s));
        }
    }

}
