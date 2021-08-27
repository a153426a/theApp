package org.firstclass.kahoot.messages.received;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Loki
 */
@Getter
@Setter
public class SelectAnswerReceivedMessage extends KahootReceivedMessage
{
    
    public SelectAnswerReceivedMessage()
    {
        type = KahootReceivedMessageType.SELECT_ANSWER;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( type.toString() ).append( " message" ).append( "\n" );
        sb.append( "Selection: " );
        var selection = getPayload().getSelection();
        if(selection!=null)
        {
            sb.append( selection.toString() );
        }
        
        return sb.toString();
    }
    
}
