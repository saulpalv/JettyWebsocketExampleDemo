package com.webSocket.parent;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;

@WebSocket
public abstract class AbstractHandler {

    protected Session session = null;

    @OnWebSocketMessage
    public void onWebSocketText(Session session, String message) throws IOException {
        System.out.println("Message received:" + message);
        processMessage(message);
    }

    @OnWebSocketConnect
    public void onWebSocketConnect(Session session) throws IOException {
        this.session = session;
        System.out.println(session.getRemoteAddress().getHostString() + " connected!");
    }

    @OnWebSocketClose
    public void onWebSocket(Session session, int status, String reason) {
        System.out.println(session.getRemoteAddress().getHostString() + " closed!");
    }

    @OnWebSocketError
    public void onWebSocketError(Session session, Throwable error) throws IOException {
        error.printStackTrace(System.err);
    }

    public abstract void processMessage(String message);

    public abstract void sendMessage(String message);

}