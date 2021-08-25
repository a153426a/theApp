package org.firstclass.messages.received;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Loki
 */
@Getter
@Setter
public class SelectAnswerReceivedMessage extends KahootReceivedMessage
{
    
    protected KahootSelections selection;
    
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
        if(selection!=null)
        {
            sb.append( selection.toString() );
        }
        
        return sb.toString();
    }
    
}
