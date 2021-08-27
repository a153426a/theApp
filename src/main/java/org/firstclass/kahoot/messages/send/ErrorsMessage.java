package org.firstclass.kahoot.messages.send;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Loki
 */
@Getter
@Setter
public class ErrorsMessage extends KahootSendMessage
{
    
    public ErrorsMessage()
    {
        type = KahootSendMessageType.ERRORS;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( type.toString() ).append( " message" ).append( "\n" );
        var errorInfo = getPayload().getErrorInfo();
        if( errorInfo !=null)
        {
            sb.append( "Info: " ).append( errorInfo ).append( ". " );
        }
        
        return sb.toString();
    }
}
