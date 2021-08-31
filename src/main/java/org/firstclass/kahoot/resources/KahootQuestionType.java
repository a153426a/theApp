package org.firstclass.kahoot.resources;

import org.firstclass.kahoot.messages.received.KahootReceivedMessageType;

/**
 * @author Loki
 * @date 31/08/21
 */
public enum KahootQuestionType
{
    
    STANDARD,
    TRUEORFALSE,
    TYPEANSWER;
    
    public static KahootQuestionType get(String string)
    {
        try
        {
            return KahootQuestionType.valueOf( string.toUpperCase() );
        }
        catch ( IllegalArgumentException e )
        {
            return null;
        }
    }
    
}
