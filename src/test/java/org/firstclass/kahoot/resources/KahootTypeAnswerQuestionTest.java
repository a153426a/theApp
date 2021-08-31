package org.firstclass.kahoot.resources;

import org.firstclass.kahoot.messages.received.KahootSelections;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Loki
 * @date 31/08/21
 */
class KahootTypeAnswerQuestionTest
{
    
    @Test
    void createKahootTypeAnswerQuestionTest()
    {
        var question = TestDataHelper.createTypeAnswerQuestion();
        assertThat(question.questionResult( "answer" ) ).isTrue();
        assertThat(question.questionResult( "answer  " ) ).isTrue();
        assertThat(question.questionResult( "answer  lala" ) ).isFalse();
    }
    
}
