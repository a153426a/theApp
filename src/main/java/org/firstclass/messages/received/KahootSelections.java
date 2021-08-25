package org.firstclass.messages.received;

/**
 * @author Loki
 */
public enum KahootSelections
{
    ONE,
    TWO,
    THREE,
    FOUR;
    
    public static KahootSelections get(String string)
    {
        try
        {
            return KahootSelections.valueOf( string.toUpperCase() );
        }
        catch ( IllegalArgumentException e )
        {
            return null;
        }
    }
}
