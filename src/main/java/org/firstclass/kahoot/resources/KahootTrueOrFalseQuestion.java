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
public class KahootTrueOrFalseQuestion extends KahootQuestion
{
    
    private KahootSelections answer;
    
    public KahootTrueOrFalseQuestion(String questionString)
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
        return kahootSelections == answer;
    }
    
}
