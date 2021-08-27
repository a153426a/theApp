package org.firstclass.kahoot.messages.send;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Loki
 */
@Getter
@Setter
public class RoomStatusBeforeGameMessage extends KahootSendMessage
{
    public RoomStatusBeforeGameMessage()
    {
        type = KahootSendMessageType.ROOM_STATUS_BEFORE_GAME;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( type.toString() ).append( " message" ).append( "\n" );
        sb.append( "User: " );
        var users = getPayload().getUsers();
        if(users!=null)
        {
            users.forEach( userName -> sb.append( userName ).append( ", " ) );
        }
        sb.append( "are in room. " );
        
        return sb.toString();
    }
}
