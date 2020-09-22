package com.webSocket.StringReverse;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;

/**
 * Example WebSocket, simple echo
 */
@WebSocket
public class StringReverseHandler {

    Session session = null;

    public StringReverseHandler(){
        System.out.println("Socket Handler Crated : Reverse");
    }

    @OnWebSocketMessage
    public void onWebSocketText(Session session, String message) throws IOException {
        System.out.println("Message received:" + message);
        sendMessage(new StringBuilder(message).reverse().toString());
    }

    public void sendMessage(String message) throws IOException {
        if (session.isOpen()) {
            session.getRemote().sendString(message);
            System.out.println("Message sent:" + message);
        }
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
}