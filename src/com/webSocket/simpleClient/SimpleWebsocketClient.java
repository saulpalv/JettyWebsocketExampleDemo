package com.webSocket.simpleClient;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

@WebSocket
public class SimpleWebsocketClient {

    private Session session;

    final CountDownLatch latch = new CountDownLatch(1);

    public void connectAndWait(String direction) {
        WebSocketClient client = new WebSocketClient();
        try {
            client.start();
            URI uri = new URI(direction);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(this, uri, request);
            waitUntilConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketMessage
    public void onText(Session session, String message) throws IOException {
        System.out.println("Message received from server:" + message);
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connected to server");
        this.session = session;
        latch.countDown();
    }

    public void sendMessage(String str) {
        try {
            session.getRemote().sendString(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitUntilConnection() throws InterruptedException {
        latch.await();
    }
}
