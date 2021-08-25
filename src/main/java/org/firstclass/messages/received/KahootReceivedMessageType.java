package org.firstclass.messages.received;

/**
 * @author Loki
 */
public enum KahootReceivedMessageType
{
    SELECT_ANSWER,
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
