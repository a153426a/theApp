package org.firstclass.kahoot.resources;

import lombok.Getter;
import lombok.Setter;
import org.firstclass.kahoot.messages.received.KahootSelections;

import java.util.Set;

/**
 * @author Loki
 * @date 31/08/21
 */
@Setter
@Getter
public class KahootStandardQuestion extends KahootQuestion
{
    
    private Set<KahootSelections> standardAnswer;
    
    public KahootStandardQuestion(String questionString, String answerOne, String answerTwo)
    {
        
        super();
        this.type = KahootQuestionType.STANDARD;
        this.questionString = questionString;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        
    }
    
    public KahootStandardQuestion(String questionString, String answerOne, String answerTwo, String answerThree)
    {
        
        super();
        this.type = KahootQuestionType.STANDARD;
        this.questionString = questionString;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        
    }
    
    public KahootStandardQuestion(String questionString, String answerOne, String answerTwo, String answerThree, String answerFour)
    {
        
        super();
        this.type = KahootQuestionType.STANDARD;
        this.questionString = questionString;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        
    }
    
    public boolean questionResult(Set<KahootSelections> selection)
    {
        if(selection.isEmpty()) return false;
        
        return standardAnswer.size() == selection.size() && standardAnswer.containsAll( selection );
    }
    
    @Override
    public void validate()
    {
        if(type == null || questionString==null || answerOne==null || answerTwo==null || standardAnswer ==null)
        {
            LOGGER.info( "Standard questions validation failed, type || questionString || answerOne || answerTwo || answer missing. " );
        }
    }
    
}
