package org.firstclass.kahoot.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Loki
 * @date 1/09/21
 */
@QuarkusTest
class KahootQuizTest
{

    @Test
    void createQuizTest()
    {
        var quiz = TestDataHelper.createQuiz();
        assertThat(quiz.getQuestionList().size()).isEqualTo( 5 );
    }
    
    @Test
    void addQuestionTest()
    {
        var quiz = TestDataHelper.createQuiz();
        assertThat(quiz.getQuestionList().size()).isEqualTo( 5 );
        
        quiz.addQuestion( TestDataHelper.createTypeAnswerQuestion() );
        assertThat(quiz.getQuestionList().size()).isEqualTo( 6 );
    }
    
    @Test
    void deleteQuestionTest()
    {
        var quiz = TestDataHelper.createQuiz();
        assertThat(quiz.getQuestionList().size()).isEqualTo( 5 );
        
        quiz.deleteQuestion( quiz.getQuestionList().get( 0 ).getId() );
        assertThat(quiz.getQuestionList().size()).isEqualTo( 4 );
    }
    
    @Test
    void deleteQuestionTestFail()
    {
        var quiz = TestDataHelper.createQuiz();
        assertThat(quiz.getQuestionList().size()).isEqualTo( 5 );
        
        quiz.deleteQuestion( RandomUtils.nextInt() );
        assertThat(quiz.getQuestionList().size()).isEqualTo( 5 );
    }
    
    @Test
    void editQuestionTest()
    {
        var quiz = TestDataHelper.createQuiz();
        assertThat(quiz.getQuestionList().size()).isEqualTo( 5 );
        var oldQuestion = quiz.getQuestionList().get( 0 );
        var newQuestion = TestDataHelper.createFourStandardQuestion();
        var questionId = quiz.getQuestionList().get( 0 ).getId();
        newQuestion.setId( questionId );
        quiz.editQuestion( newQuestion );
        assertThat( quiz.getQuestionList().get( 0 ).getQuestionString() ).isEqualTo( newQuestion.getQuestionString() );
    }

}
