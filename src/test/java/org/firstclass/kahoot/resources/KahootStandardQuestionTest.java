package org.firstclass.kahoot.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.firstclass.kahoot.messages.received.KahootSelections;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Loki
 * @date 31/08/21
 */
@QuarkusTest
class KahootStandardQuestionTest
{

    @Test
    void createTwoSelectionsQuestion()
    {
        var question = TestDataHelper.createTwoStandardQuestion();
        assertThat(question.questionResult( Stream.of( KahootSelections.ONE ).collect( Collectors.toSet()) )).isTrue();
    }
    
    @Test
    void createThreeSelectionsQuestion()
    {
        var question = TestDataHelper.createThreeStandardQuestion();
        assertThat(question.questionResult( Stream.of( KahootSelections.ONE ).collect( Collectors.toSet()) )).isTrue();
    }
    
    @Test
    void createFourSelectionsQuestion()
    {
        var question = TestDataHelper.createFourStandardQuestion();
        assertThat(question.questionResult( Stream.of( KahootSelections.ONE ).collect( Collectors.toSet()) )).isTrue();
    }
    
    @Test
    void createFourSelectionsQuestionMultipleAnswers()
    {
        var question = TestDataHelper.createFourStandardQuestion();
        question.setStandardAnswer( Stream.of( KahootSelections.ONE, KahootSelections.TWO, KahootSelections.THREE ).collect( Collectors.toSet()) );
        assertThat(question.questionResult( Stream.of( KahootSelections.ONE ).collect( Collectors.toSet()) )).isFalse();
        assertThat(question.questionResult( Stream.of( KahootSelections.ONE, KahootSelections.TWO ).collect( Collectors.toSet()) )).isFalse();
        assertThat(question.questionResult( Stream.of( KahootSelections.ONE, KahootSelections.TWO, KahootSelections.THREE )
                .collect( Collectors.toSet()) )).isTrue();
        assertThat(question.questionResult( Stream.of( KahootSelections.ONE, KahootSelections.TWO, KahootSelections.THREE, KahootSelections.FOUR )
                .collect( Collectors.toSet()) )).isFalse();
    }

}
