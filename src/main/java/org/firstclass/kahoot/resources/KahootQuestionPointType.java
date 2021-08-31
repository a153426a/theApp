package org.firstclass.kahoot.resources;

/**
 * @author Loki
 * @date 31/08/21
 */
public enum KahootQuestionPointType
{
    
    STANDARD,
    DOUBLEPOINTS,
    NOPOINTS;
    
    public static KahootQuestionPointType get(String string)
    {
        try
        {
            return KahootQuestionPointType.valueOf( string.toUpperCase() );
        }
        catch ( IllegalArgumentException e )
        {
            return null;
        }
    }

}
