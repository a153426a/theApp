package org.firstclass.kahoot.resources;

import lombok.Getter;
import lombok.Setter;
import org.jboss.logging.Logger;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Loki
 * @date 10/08/21
 */
@Getter
@Setter
public class Room
{
    private static final Logger LOGGER = Logger.getLogger( Room.class );
    private String               roomId;
    private Map<String, Session> users = new ConcurrentHashMap<>();;
    
    public Room() {}
    
    public Room(String roomId)
    {
        this.roomId = roomId;
    }
    
    public Map<String, Session> addUser(String userName, Session session)
    {

        if(users.containsKey( userName ))
        {
            
            LOGGER.info( String.format( "Adding user: %s fail. Check if the user exists ", userName ) );
            
        } else
        {
            
            users.put( userName, session );
            LOGGER.info( String.format( "Adding user: %s success ", userName ) );
            
        }
    
        return users;

    }

    public Map<String, Session> removeUser(String userName)
    {
        if( !users.containsKey( userName ))
        {
            
            LOGGER.info( String.format( "Removing user: %s fail. Check if the user exists ", userName ) );
            
        } else
        {
            
            users.remove( userName );
            LOGGER.info( String.format( "Removing user: %s success ", userName ) );
            
        }

        return users;

    }
    
}
