package com.webSocket.StringToUppercase;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@SuppressWarnings("serial")
@WebServlet(name = "WebSocket Servlet", urlPatterns = {"/reverse"})
public class StringUppercaseServlet extends WebSocketServlet
{

    @Override
    public void configure(WebSocketServletFactory factory)
    {
        // set session idle timeout
        factory.getPolicy().setIdleTimeout(Long.MAX_VALUE);

        // register MyEchoSocket as the WebSocket to create on Upgrade
        factory.register(StringUppercaseHandler.class);
    }
}
