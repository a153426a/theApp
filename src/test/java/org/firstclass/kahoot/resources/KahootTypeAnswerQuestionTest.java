package org.firstclass.kahoot.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Loki
 * @date 31/08/21
 */
@QuarkusTest
class KahootTypeAnswerQuestionTest
{
    
    @Test
    void createKahootTypeAnswerQuestionTest()
    {
        var question = TestDataHelper.createTypeAnswerQuestion();
        assertThat(question.checkResult( "answer" ) ).isTrue();
        assertThat(question.checkResult( "answer  " ) ).isTrue();
        assertThat(question.checkResult( "answer  lala" ) ).isFalse();
    }
    
}
