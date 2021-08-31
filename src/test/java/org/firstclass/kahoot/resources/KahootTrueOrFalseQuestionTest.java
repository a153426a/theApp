package org.firstclass.kahoot.resources;

import org.firstclass.kahoot.messages.received.KahootSelections;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Loki
 * @date 31/08/21
 */
class KahootTrueOrFalseQuestionTest
{
    
    @Test
    void createFourSelectionsQuestionTest()
    {
        var question = TestDataHelper.createTrueOrFalseQuestion();
        assertThat(question.questionResult( KahootSelections.ONE ) ).isTrue();
        assertThat(question.questionResult( KahootSelections.TWO ) ).isFalse();
    }
    
}
