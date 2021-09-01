package org.firstclass.kahoot.resources;

import org.apache.commons.lang3.RandomStringUtils;
import org.firstclass.kahoot.messages.received.KahootSelections;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Loki
 * @date 31/08/21
 */
public class TestDataHelper
{
    
    public static KahootStandardQuestion createTwoStandardQuestion()
    {
        var result = new KahootStandardQuestion( RandomStringUtils.randomAlphabetic( 100 ),
                RandomStringUtils.randomAlphabetic( 20 ),
                RandomStringUtils.randomAlphabetic( 20 ));
        
        result.setStandardAnswer( Stream.of( KahootSelections.ONE ).collect( Collectors.toSet()) );
        return result;
    }
    
    public static KahootStandardQuestion createThreeStandardQuestion()
    {
        var result = new KahootStandardQuestion( RandomStringUtils.randomAlphabetic( 100 ),
                RandomStringUtils.randomAlphabetic( 20 ),
                RandomStringUtils.randomAlphabetic( 20 ),
                RandomStringUtils.randomAlphabetic( 20 ));
        
        result.setStandardAnswer( Stream.of( KahootSelections.ONE ).collect( Collectors.toSet()) );
        return result;
    }
    
    public static KahootStandardQuestion createFourStandardQuestion()
    {
        var result = new KahootStandardQuestion( RandomStringUtils.randomAlphabetic( 100 ),
                RandomStringUtils.randomAlphabetic( 20 ),
                RandomStringUtils.randomAlphabetic( 20 ),
                RandomStringUtils.randomAlphabetic( 20 ),
                RandomStringUtils.randomAlphabetic( 20 ));
        
        result.setStandardAnswer( Stream.of( KahootSelections.ONE ).collect( Collectors.toSet()) );
        return result;
    }
    
    public static KahootTrueOrFalseQuestion createTrueOrFalseQuestion()
    {
        var result = new KahootTrueOrFalseQuestion( RandomStringUtils.randomAlphabetic( 100 ), "true", "false" );
        
        result.setTfAnswer( KahootSelections.ONE );
        
        return result;
    }
    
    public static KahootTypeAnswerQuestion createTypeAnswerQuestion()
    {
        var result = new KahootTypeAnswerQuestion( RandomStringUtils.randomAlphabetic( 100 ) );
        
        result.setTypeAnswer( "answer");
        
        return result;
    }
    
    public static KahootQuiz createQuiz()
    {
        var result = new KahootQuiz( RandomStringUtils.randomAlphabetic( 20 ), RandomStringUtils.randomAlphabetic( 100 ) );
        result.addQuestion( createTwoStandardQuestion() );
        result.addQuestion( createThreeStandardQuestion() );
        result.addQuestion( createFourStandardQuestion() );
        result.addQuestion( createTrueOrFalseQuestion() );
        result.addQuestion( createTypeAnswerQuestion() );
        
        return result;
    }

}
