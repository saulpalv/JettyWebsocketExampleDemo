package com.webSocket.StringToUppercase;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;

/**
 * Example WebSocket, simple echo
 */
@WebSocket
public class StringUppercaseHandler {

    Session session = null;

    public StringUppercaseHandler(){
        System.out.println("Socket Handler Crated : Uppercase");
    }

    @OnWebSocketMessage
    public void onWebSocketText(Session session, String message) throws IOException {
        System.out.println("Message received:" + message);
        sendMessage(message.toUpperCase());
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