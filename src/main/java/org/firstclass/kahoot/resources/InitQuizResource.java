package org.firstclass.kahoot.resources;

import org.firstclass.kahoot.messages.received.KahootSelections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Loki
 * @date 1/09/21
 */
public class InitQuizResource
{
    private InitQuizResource()
    {
    
    }
    
    protected static List<KahootQuiz> quizzes = new ArrayList<>();
    
    public static List<KahootQuiz> init()
    {
        var quizOne = new KahootQuiz( "Test Quiz - One" );
        quizOne.setQuestionList( createQuestionsList() );
        var quizTwo = new KahootQuiz( "Test Quiz - Two" );
        quizTwo.setQuestionList( createQuestionsList() );
        var quizThree = new KahootQuiz( "Test Quiz - Three" );
        quizThree.setQuestionList( createQuestionsList() );
        
        quizzes.add( quizOne );
        quizzes.add( quizTwo );
        quizzes.add( quizThree );
        
        return quizzes;
    }
    
    private static List<KahootQuestion> createQuestionsList()
    {
        List<KahootQuestion> result = new ArrayList<>();
        var standardQuestionOne = new KahootStandardQuestion( "This is a standard question with two selections, "
                + "you can have 2 - 4 selections in the question, "
                + "for this one, correct answer is: ",
                "One",
                "Two" );
        standardQuestionOne.setStandardAnswer( getRandomXSelectionsFromSet( List.of( KahootSelections.ONE, KahootSelections.TWO ), 1 ) );
        standardQuestionOne.setQuestionString( standardQuestionOne.getQuestionString().concat( standardQuestionOne.getStandardAnswer().toString() ) );
        
        var standardQuestionTwo = new KahootStandardQuestion( "This is a standard question with three selections, "
                + "one or more of them can be correct"
                + "the rule can be changed later "
                + "for this one, correct answer is: ",
                "One",
                "Two",
                "Three" );
        standardQuestionTwo.setStandardAnswer( getRandomXSelectionsFromSet( List.of(KahootSelections.ONE, KahootSelections.TWO, KahootSelections.THREE), 1 ) );
        standardQuestionTwo.setQuestionString( standardQuestionTwo.getQuestionString().concat( standardQuestionTwo.getStandardAnswer().toString() ) );
    
        var standardQuestionThree = new KahootStandardQuestion( "This is a standard question with four selections, "
                + "for this one, correct answer is: ",
                "One",
                "Two",
                "Three",
                "Four");
        standardQuestionThree.setStandardAnswer( getRandomXSelectionsFromSet( List.of(KahootSelections.ONE, KahootSelections.TWO, KahootSelections.THREE, KahootSelections.FOUR), 1 ) );
        standardQuestionThree.setQuestionString( standardQuestionThree.getQuestionString().concat( standardQuestionThree.getStandardAnswer().toString() ) );
    
        var standardQuestionFour = new KahootStandardQuestion( "This is a standard question with four selections, "
                + "this has multiple corrections, you need to select them all to be CORRECT"
                + "for this one, correct answer is: ",
                "One",
                "Two",
                "Three",
                "Four");
        standardQuestionFour.setStandardAnswer( getRandomXSelectionsFromSet( List.of(KahootSelections.ONE, KahootSelections.TWO, KahootSelections.THREE, KahootSelections.FOUR), 2 ) );
        standardQuestionFour.setQuestionString( standardQuestionFour.getQuestionString().concat( standardQuestionFour.getStandardAnswer().toString() ) );
    
        var trueOrFalseQuestion = new KahootTrueOrFalseQuestion( "This is a true or false quesion, "
                + "only one of the selections can be correct, "
                + "ture or false selections can be customerize to any string else"
                + "for this one, correct answer is: ",
                "true",
                "false"
        );
        trueOrFalseQuestion.setTfAnswer( getRandomSelectionFromSet(List.of(KahootSelections.ONE, KahootSelections.TWO)) );
        trueOrFalseQuestion.setQuestionString( trueOrFalseQuestion.getQuestionString().concat( trueOrFalseQuestion.getTfAnswer().toString() ) );
    
        var typeAnswerQuestion = new KahootTypeAnswerQuestion( "This is a type answer question, "
                + "answer need to be exact same as the correct answer to be correct, "
                + "spaces will be trimmed from the input answer, "
                + "for this one, correct answer is: ");
        typeAnswerQuestion.setTypeAnswer( "test_answer" );
        typeAnswerQuestion.setQuestionString( typeAnswerQuestion.getQuestionString().concat( typeAnswerQuestion.getTypeAnswer().toString() ) );
        
        result.add( standardQuestionOne );
        result.add( standardQuestionTwo );
        result.add( standardQuestionThree );
        result.add( standardQuestionFour );
        result.add( trueOrFalseQuestion );
        result.add( typeAnswerQuestion );
    
        return result;
        
    }
    
    private static Set<KahootSelections> getRandomXSelectionsFromSet( List<KahootSelections> selections, int x )
    {
        Set<KahootSelections> result = new HashSet<>();
        Set<Integer> rands = new HashSet<>();
        
        for ( int i = 0; i < x; i++ )
        {
            
            while ( rands.size() != i + 1 )
            {
                rands.add( new Random().nextInt( selections.size() ) );
            }
            
        }
        
        for ( int j : rands )
        {
            result.add( selections.get( j ) );
        }
        
        return result;
    }
    
    private static KahootSelections getRandomSelectionFromSet( List<KahootSelections> selections)
    {
        return selections.get( new Random().nextInt( selections.size() ) );
    }
    
}
