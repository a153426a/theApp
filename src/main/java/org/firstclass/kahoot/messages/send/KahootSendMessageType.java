package org.firstclass.kahoot.messages.send;

import org.firstclass.kahoot.messages.received.KahootReceivedMessageType;

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