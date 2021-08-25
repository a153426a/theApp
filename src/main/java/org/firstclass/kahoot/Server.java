package org.firstclass.kahoot;

import org.firstclass.kahoot.resources.RoomResources;
import org.firstclass.messages.received.KahootReceivedMessage;
import org.firstclass.messages.received.KahootReceivedMessageDecoder;
import org.firstclass.messages.received.OtherReceivedMessage;
import org.firstclass.messages.received.SelectAnswerReceivedMessage;
import org.firstclass.messages.send.ErrorsMessage;
import org.firstclass.messages.send.KahootSendMessage;
import org.firstclass.messages.send.KahootSendMessageEncoder;
import org.firstclass.messages.send.RoomStatusBeforeGameMessage;
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
@ServerEndpoint(
        value = "/kahoot/{room}/{username}",
        encoders = { KahootSendMessageEncoder.class },
        decoders = { KahootReceivedMessageDecoder.class }
)
@ApplicationScoped
public class Server
{
    @Inject
    RoomResources roomResources;
    
    private static final String UNABLE_SEND_MESSAGE = "Unable to send message: %s";
    
    private static final String WRONG_MESSAGE_TYPE = "Wrong messageType, please check again. ";
    
    private static final Logger LOGGER = Logger.getLogger( Server.class );
    
    @OnOpen
    public void onOpen(Session session, @PathParam( "room" ) String roomId, @PathParam("username") String username)
    {
        var result = roomResources.getRoom( roomId ).addUser( username, session );
        
        if(result)
        {
            
            var roomStatusMessage = new RoomStatusBeforeGameMessage();
            roomStatusMessage.setUsers( roomResources.getRoom( roomId ).getUsers().keySet() );
            broadcast( roomId, roomStatusMessage);
            
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
    public void onMessage( KahootReceivedMessage message,  @PathParam( "room" ) String roomId, @PathParam("username") String username)
    {
        
        if ( message != null )
        {
    
            switch ( message.getType() )
            {
                case SELECT_ANSWER:
                    onReceiveSelectAnswerMessage( (SelectAnswerReceivedMessage) message, roomId, username );
                    break;
                case OTHER:
                    onReceiveOtherMessage( (OtherReceivedMessage ) message, roomId, username );
                    break;
            }
            
        } else
        {
            LOGGER.info( "Message received is null, sending error message. " );
            var errorMessage = new ErrorsMessage();
            errorMessage.setInfo( WRONG_MESSAGE_TYPE );
            privateMessage( roomId, username, errorMessage );
        }
        
        
        LOGGER.info( String.format( "User: %s sends %s in room: %s", username, message, roomId) );
        broadcast(roomId, String.format( ">> %s: %s", username, message));
        
    }
    
    private void onReceiveSelectAnswerMessage(SelectAnswerReceivedMessage message, String roomId, String username)
    {
    
    }
    
    private void onReceiveOtherMessage( OtherReceivedMessage message, String roomId, String username )
    {
    
    }
    
    private void broadcast(String roomId, String string)
    {
        
        roomResources.getRoom( roomId ).getUsers().values().forEach(s -> {
            s.getAsyncRemote().sendObject(string, result -> {
                if (result.getException() != null) {
                    LOGGER.info( String.format( UNABLE_SEND_MESSAGE, result.getException()) );
                }
            });
        });
        LOGGER.info( String.format( "String message broadcast to room: %s with message: %n%s", roomId, string ) );
    }
    
    private void broadcast(String roomId, KahootSendMessage message )
    {
        
        roomResources.getRoom( roomId ).getUsers().values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    LOGGER.info( String.format( UNABLE_SEND_MESSAGE, result.getException()) );
                }
            });
        });
        LOGGER.info( String.format( "Message broadcast to room: %s with message: %n%s", roomId, message ) );
        
    }
    
    private void privateMessage(String roomId, String username, KahootSendMessage message)
    {
        
        roomResources
                .getRoom( roomId )
                .getUsers()
                .get( username )
                .getAsyncRemote()
                .sendObject(message, result -> {
                    if (result.getException() != null) {
                        LOGGER.info( String.format( UNABLE_SEND_MESSAGE, result.getException()) );
                    }
                });
        LOGGER.info( String.format( "Message send to user: %s in room: %s with message: %n%s", username, roomId, message ) );
        
    }
    
}
