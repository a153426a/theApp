package org.firstclass.kahoot;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.net.URI;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author Loki
 * @date 7/08/21
 */
@QuarkusTest
public class ServerTest
{
    private static final LinkedBlockingDeque<String> MESSAGES = new LinkedBlockingDeque<>();
    
    @TestHTTPResource("/kahoot/loki/lokiloki")
    URI uri;
    
    @Test
    public void testWebsocketChat() throws Exception {
        try ( Session session = ContainerProvider.getWebSocketContainer().connectToServer(Client.class, uri)) {
            Assertions.assertEquals("CONNECT", MESSAGES.poll(10, TimeUnit.SECONDS));
//            Assertions.assertEquals("User lokiloki has joined room: loki ", MESSAGES.poll(10, TimeUnit.SECONDS));
//            session.getAsyncRemote().sendText("hello world");
//            Assertions.assertEquals(">> lokiloki: hello world", MESSAGES.poll(10, TimeUnit.SECONDS));
        }
    }
    
    @ClientEndpoint
    public static class Client {
        
        @OnOpen
        public void open(Session session) {
            MESSAGES.add("CONNECT");
        }
        
        @OnMessage
        void message(String msg) {
            MESSAGES.add(msg);
        }
        
    }
}