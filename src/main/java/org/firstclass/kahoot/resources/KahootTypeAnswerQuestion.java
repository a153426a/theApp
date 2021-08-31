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
public class KahootTypeAnswerQuestion extends KahootQuestion
{
    
    private String answer;
    
    public KahootTypeAnswerQuestion(String questionString)
    {
        
        super();
        this.type = KahootQuestionType.TYPEANSWER;
        this.questionString = questionString;
    }
    
    public boolean questionResult( String submittedAnswer)
    {
        return submittedAnswer.trim().equals( answer );
    }
    
}
