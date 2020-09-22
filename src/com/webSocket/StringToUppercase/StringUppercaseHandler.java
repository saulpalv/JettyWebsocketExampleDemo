package com.webSocket.StringToUppercase;

import com.webSocket.parent.AbstractHandler;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

@WebSocket
public class StringUppercaseHandler extends AbstractHandler {

    public StringUppercaseHandler(){
        System.out.println("Socket Handler Crated : Uppercase");
    }

    @Override
    public void processMessage(String message) {
        sendMessage(message.toUpperCase());
    }

    @Override
    public void sendMessage(String message) {
        if (session.isOpen()) {
            try {
                session.getRemote().sendString(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Message sent:" + message);
        }
    }
}