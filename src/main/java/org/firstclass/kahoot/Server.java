package org.firstclass.kahoot;


import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Loki
 * @date 7/08/21
 */
@ServerEndpoint("/kahoot/{username}")
@ApplicationScoped
public class Server
{
    
    private static final Logger LOGGER = Logger.getLogger( Server.class );
    
    Map<String, Session> sessions = new ConcurrentHashMap<>();
    
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        
        sessions.put(username, session);
        LOGGER.info( String.format( "%s has joined the room. ", username ) );
        broadcast( "User " + username + " has joined the room. " );
        
    }
    
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        
        sessions.remove(username);
        LOGGER.info( String.format( "%s has left the room. ", username ) );
        broadcast("User " + username + " left");
        
    }
    
    @OnError
    public void onError(Session session, @PathParam("username") String username, Throwable throwable) {
        
        sessions.remove(username);
        LOGGER.error("onError", throwable);
        broadcast("User " + username + " left on error: " + throwable);
        
    }
    
    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) {
        
        LOGGER.info( String.format( "%s sends %s", username, message) );
        broadcast(">> " + username + ": " + message);
        
    }
    
    private void broadcast(String message) {
        
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    LOGGER.info( String.format( "Unable to send message: %s", result.getException()) );
                }
            });
        });
    }
    
}
