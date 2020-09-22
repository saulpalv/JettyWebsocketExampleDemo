package com.webSocket.StringReverse;

import com.webSocket.parent.AbstractHandler;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;

@WebSocket
public class StringReverseHandler extends AbstractHandler {

    public StringReverseHandler(){
        System.out.println("Socket Handler Crated : Reverse");
    }

    @Override
    public void processMessage(String message) {
        sendMessage(new StringBuilder(message).reverse().toString());
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