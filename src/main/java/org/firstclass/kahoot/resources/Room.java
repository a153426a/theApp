package org.firstclass.kahoot.resources;

import lombok.Getter;
import lombok.Setter;
import org.jboss.logging.Logger;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Loki
 * @date 10/08/21
 */
@Getter
@Setter
public class Room
{
    private static final Logger LOGGER = Logger.getLogger( Room.class );
    private              String roomId;
    private Set<String> users;
    
    public Room() {}
    
    public Room(String roomId)
    {
        this.roomId = roomId;
    }
    
    public Set<String> addUser(String userName)
    {
        if(users==null) users = new HashSet<>();
        
        var result = users.add(userName);
    
        if( result )
        {
            LOGGER.info( String.format( "Adding user: %s success ", userName ) );
        } else
        {
            LOGGER.info( String.format( "Adding user: %s fail. Check if the user exists ", userName ) );
        }
        
        return users;
        
    }
    
    public Set<String> removeUser(String userName)
    {
        if(users==null || users.isEmpty())
        {
            LOGGER.info( "There are no users in the room" );
            return Collections.emptySet();
        }
    
        var result = users.removeIf( existingUser -> existingUser.contentEquals( userName ) );
    
        if( result )
        {
            LOGGER.info( String.format( "Removing user: %s success ", userName ) );
        } else
        {
            LOGGER.info( String.format( "Removing user: %s fail. Check if the user exists ", userName ) );
        }
    
        return users;
    
    }
    
}
