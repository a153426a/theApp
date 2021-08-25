package org.firstclass.messages.send;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Loki
 */
@Getter
@Setter
public class ErrorsMessage extends KahootSendMessage
{
    
    private String info;
    
    public ErrorsMessage()
    {
        type = KahootSendMessageType.ERRORS;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( type.toString() ).append( " message" ).append( "\n" );
        
        if(info!=null)
        {
            sb.append( "Info: " ).append( info ).append( ". " );
        }
        
        return sb.toString();
    }
}
