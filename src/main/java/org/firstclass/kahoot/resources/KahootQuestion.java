package org.firstclass.kahoot.resources;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.firstclass.kahoot.messages.received.KahootSelections;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Loki
 * @date 31/08/21
 */
@Getter
@Setter
public abstract class KahootQuestion
{
    
    KahootQuestionType      type;
    KahootQuestionPointType pointType = KahootQuestionPointType.STANDARD;
    private static final AtomicInteger count = new AtomicInteger( 0 );
    private              int           id;
    String questionString = null;
    String imageUrl       = null;
    String answerOne      = null;
    String answerTwo      = null;
    String answerThree    = null;
    String answerFour     = null;
    
    int timeLimit = 20;
    
    protected KahootQuestion()
    {
        this.id = count.incrementAndGet();
    }
    
}
