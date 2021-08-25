package org.firstclass.messages.send;

import org.firstclass.messages.received.KahootReceivedMessageType;

/**
 * @author Loki
 */
public enum KahootSendMessageType
{
    ROOM_STATUS_BEFORE_GAME,
    QUESTION,
    CURRENT_STATISTICS,
    FINAL_STATISTICS,
    ERRORS,
    OTHER;
    
    public static KahootReceivedMessageType get(String string)
    {
        try
        {
            return KahootReceivedMessageType.valueOf( string.toUpperCase() );
        }
        catch ( IllegalArgumentException e )
        {
            return null;
        }
    }
    
}