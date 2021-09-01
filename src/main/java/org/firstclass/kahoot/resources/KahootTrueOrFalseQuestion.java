package org.firstclass.kahoot.resources;

import lombok.Getter;
import lombok.Setter;
import org.firstclass.kahoot.messages.received.KahootSelections;

/**
 * @author Loki
 * @date 31/08/21
 */
@Setter
@Getter
public class KahootTrueOrFalseQuestion extends KahootQuestion
{
    
    private KahootSelections      tfAnswer;
    
    public KahootTrueOrFalseQuestion(String questionString, String answerOne, String answerTwo)
    {
        
        super();
        this.type = KahootQuestionType.TRUEORFALSE;
        this.questionString = questionString;
        // true
        this.answerOne = answerOne;
        // false
        this.answerTwo = answerTwo;
        
    }
    
    public boolean questionResult( KahootSelections kahootSelections)
    {
        return kahootSelections == tfAnswer;
    }
    
    @Override
    public void validate()
    {
        if(type == null || questionString==null || answerOne==null || answerTwo==null || tfAnswer ==null)
        {
            LOGGER.info( "Standard questions validation failed, type || questionString || answerOne || answerTwo || answer missing. " );
        }
    }
    
}
