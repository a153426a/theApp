package org.firstclass.kahoot;

import org.firstclass.kahoot.resources.RoomResources;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Loki
 * @date 7/08/21
 */
@ServerEndpoint("/kahoot/{room}/{username}")
@ApplicationScoped
public class Server
{
    @Inject
    RoomResources roomResources;
    
    private static final Logger LOGGER = Logger.getLogger( Server.class );
    
    @OnOpen
    public void onOpen(Session session, @PathParam( "room" ) String roomId, @PathParam("username") String username)
    {
        var result = roomResources.getRoom( roomId ).addUser( username, session );
        
        if(result)
        {
            LOGGER.info( String.format( "User %s has joined room: %s ", username, roomId ) );
            broadcast( roomId, String.format( "User %s has joined room: %s ", username, roomId ) );
        }
        
    }
    
    @OnClose
    public void onClose(Session session, @PathParam( "room" ) String roomId, @PathParam("username") String username)
    {
        
        var result =  roomResources.getRoom( roomId ).removeUser( username );
        
        if(result)
        {
            LOGGER.info( String.format( "User %s has left room: %s. ", username, roomId ) );
            broadcast( roomId, String.format( "User %s has left room: %s. ", username, roomId ) );
        }
        
    }
    
    @OnError
    public void onError(Session session, @PathParam( "room" ) String roomId, @PathParam("username") String username, Throwable throwable)
    {
        
        roomResources.getRoom( roomId ).removeUser( username );
        
        LOGGER.error("onError", throwable);
        broadcast( roomId, String.format( "User %s left on error: %s", username, throwable ) );
        
    }
    
    @OnMessage
    public void onMessage(String message,  @PathParam( "room" ) String roomId, @PathParam("username") String username)
    {
        
        LOGGER.info( String.format( "User: %s sends %s in room: %s", username, message, roomId) );
        broadcast(roomId, String.format( ">> %s: %s", username, message));
        
    }
    
    private void broadcast(String roomId, String message)
    {
        
        roomResources.getRoom( roomId ).getUsers().values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    LOGGER.info( String.format( "Unable to send message: %s", result.getException()) );
                }
            });
        });
    }
    
}
