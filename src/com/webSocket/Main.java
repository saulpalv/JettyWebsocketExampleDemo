package com.webSocket;

import com.webSocket.StringReverse.StringReverseServlet;
import com.webSocket.simpleClient.StringUppercaseClient;
import com.webSocket.StringToUppercase.StringUppercaseServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Main {

    static int port = 8081;

    public static void main(String[] args) {
        StartClient("/reverse");
        StartClient("/uppercase");
        StartServer();
    }

    public static void StartServer() {
        Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(StringUppercaseServlet.class, "/uppercase");
        handler.addServletWithMapping(StringReverseServlet.class, "/reverse");
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void StartClient(String route) {
        new Thread(()-> {
            try {
                Thread.sleep(2000);
                System.out.println("starting client");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StringUppercaseClient client = new StringUppercaseClient();
            client.connectAndWait("ws://localhost:" + port + route);
            client.sendMessage("echo");
            client.sendMessage("test");
        }).start();
    }
}
