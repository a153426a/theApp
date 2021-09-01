package org.firstclass.kahoot.resources;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Loki
 * @date 31/08/21
 */
@Getter
@Setter
public class KahootQuiz
{
    
    private static final AtomicInteger        count         = new AtomicInteger( 0 );
    private              int                  id;
    private              String               title         = "";
    private              String               description   = "";
    private              List<KahootQuestion> questionList  = new ArrayList<>();
    private              String               coverImageUrl = "";
    
    public KahootQuiz( String title )
    {
        this.id = count.incrementAndGet();
        this.title = title;
    }
    
    public KahootQuiz( String title, String description )
    {
        this.id = count.incrementAndGet();
        this.title = title;
        this.description = description;
    }
    
    public int getSize()
    {
        return questionList.size();
    }
    
    public List<KahootQuestion> addQuestion( KahootQuestion kahootQuestion )
    {
        questionList.add( kahootQuestion );
        System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllll");
        return questionList;
    }
    
    public List<KahootQuestion> editQuestion( KahootQuestion kahootQuestion )
    {
        
        this.questionList = questionList
                .stream()
                .map( question -> question.getId() == kahootQuestion.getId() ? kahootQuestion : question )
                .collect( Collectors.toList());
        
        return questionList;
    }
    
    public List<KahootQuestion> deleteQuestion(int id)
    {
        questionList.removeIf( question -> question.getId() == id );
        
        return questionList;
    }
    
    public KahootQuiz update(KahootQuiz newQuiz)
    {
        this.title = newQuiz.getTitle();
        this.description = newQuiz.getDescription();
        this.questionList = newQuiz.getQuestionList();
        this.coverImageUrl = newQuiz.getCoverImageUrl();
        
        return this;
    }
    
}
