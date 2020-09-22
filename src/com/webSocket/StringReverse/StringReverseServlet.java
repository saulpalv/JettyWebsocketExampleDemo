package com.webSocket.StringReverse;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet(name = "WebSocket Servlet", urlPatterns = {"/uppercase"})
public class StringReverseServlet extends WebSocketServlet
{

    @Override
    public void configure(WebSocketServletFactory factory)
    {
        // set session idle timeout
        factory.getPolicy().setIdleTimeout(Long.MAX_VALUE);

        // register MyEchoSocket as the WebSocket to create on Upgrade
        factory.register(StringReverseHandler.class);
    }
}
