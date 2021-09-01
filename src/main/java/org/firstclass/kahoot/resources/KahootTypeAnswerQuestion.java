package org.firstclass.kahoot.resources;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Loki
 * @date 31/08/21
 */
@Setter
@Getter
public class KahootTypeAnswerQuestion extends KahootQuestion
{
    
    private String                typeAnswer;
    
    public KahootTypeAnswerQuestion(String questionString)
    {
        
        super();
        this.type = KahootQuestionType.TYPEANSWER;
        this.questionString = questionString;
    }
    
    public boolean checkResult( String submittedAnswer)
    {
        return submittedAnswer.trim().equals( typeAnswer );
    }
    
    @Override
    public void validate()
    {
        if(type == null || questionString==null || typeAnswer ==null)
        {
            LOGGER.info( "Standard questions validation failed, type || questionString || answers missing. " );
        }
    }
    
}
