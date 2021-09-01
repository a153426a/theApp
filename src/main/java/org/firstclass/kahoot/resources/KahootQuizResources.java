package org.firstclass.kahoot.resources;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Loki
 */
@ApplicationScoped
@Path("/quizzes")
@Produces( MediaType.APPLICATION_JSON )
public class KahootQuizResources
{
    
    private static final Logger LOGGER = Logger.getLogger( KahootQuizResources.class );
    
    private static final Gson gson = new Gson();
    
    private final List<KahootQuiz> quizzes;
    
    public KahootQuizResources()
    {
//        quizzes = new HashSet<>();
//        build init quizzes from helper class for now.
        quizzes = InitQuizResource.init();
    }
    
    
    
    @GET
    public List<KahootQuiz> getQuizzes()
    {
        LOGGER.info( String.format( "Listing quizzes, quizzes count: %d  ", quizzes.size() ) );
        return quizzes;
    }
    
    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    public List<KahootQuiz> addQuiz(KahootQuiz quiz)
    {
        if(quiz.getTitle()==null) throw new BadRequestException("Quiz title missing. ");
        if( quizzes.stream().noneMatch( quiz1 -> Objects.equals( quiz1.getId(), quiz.getId() ) ) )
        {
            quizzes.add( quiz );
            LOGGER.info( String.format( "Adding quiz: %s success ", quiz.getId() ) );
        } else
        {
            LOGGER.info( String.format( "Adding quiz: %s fail. The quiz exists ", quiz.getId() ) );
        }
        
        return quizzes;
    }
    
    @DELETE
    @Consumes( MediaType.APPLICATION_JSON )
    public List<KahootQuiz> deleteQuiz(String idJson)
    {
        JsonObject jsonObject = JsonParser.parseString( idJson ).getAsJsonObject();
        int id = jsonObject.getAsJsonPrimitive("id").getAsInt();
        var result = quizzes.removeIf( quiz -> quiz.getId() == id );
        
        if( result )
        {
            LOGGER.info( String.format( "Delete quiz: %s success ", idJson ) );
        } else
        {
            LOGGER.info( String.format( "Delete quiz: %s fail ", idJson ) );
            throw new BadRequestException("Delete quiz: %s fail ");
        }
        return quizzes;
    }
    
    @PUT
    @Consumes( MediaType.APPLICATION_JSON )
    public KahootQuiz updateQuiz(KahootQuiz quiz)
    {
        var oldQuiz = getQuizOrNull( quiz.getId() );
        if(oldQuiz == null)
        {
            LOGGER.info( String.format( "Edit quiz: %s fail, quiz does not exist ", quiz.getId() ) );
            throw new BadRequestException(String.format( "Edit quiz: %s fail, quiz does not exist ", quiz.getId() ));
        } else
        {
            LOGGER.info( String.format( "Edit quiz: %s success", quiz.getId() ) );
            oldQuiz.update( quiz );
            return oldQuiz;
        }
        
    }
    
    @GET
    @Path( "{id}" )
    public KahootQuiz getQuiz(int id)
    {
        var quiz = getQuizOrNull(id);
        if(quiz != null )
        {
            LOGGER.info( String.format( "Get quiz: %s success ", quiz.getId() ) );
        } else
        {
            LOGGER.info( String.format( "Get quiz: %s fail ", id ) );
        }
        return quiz;
    }
    
    @GET
    @Path( "{id}/questions" )
    public List<KahootQuestion> getQuestions(String id)
    {
        var quiz = validateQuiz( Integer.parseInt( id ), "Add question" );
        var questions = quiz.getQuestionList();
        LOGGER.info( String.format( "Listing questions, questions count: %d  ", questions.size() ) );
        return questions;
    }
    

    @POST
    @Path( "{id}/questions" )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<KahootQuestion> addQuestion(@PathParam( "id" ) String id, String payload)
    {
        var quiz = validateQuiz( Integer.parseInt( id ), "Add question" );
        var question = validateQuestion( payload );
        // TODO change this one after database deployment
        question.setId( new Random().nextInt(1048576) );
        
        var questionList = quiz.getQuestionList();
        if( questionList.stream().noneMatch( question1 -> Objects.equals( question1.getId(), question.getId() ) ) )
        {
            quiz.addQuestion( question );
            LOGGER.info( String.format( "Adding question: %s success ", question.getId() ) );
        } else
        {
            LOGGER.info( String.format( "Adding question: %s fail. The question exists ", question.getId() ) );
        }

        return quiz.getQuestionList();
    }

    @DELETE
    @Path( "{id}/questions" )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<KahootQuestion> deleteQuestion(@PathParam( "id" ) String id, String idJson)
    {
        var quiz = validateQuiz( Integer.parseInt( id ), "Delete question" );

        JsonObject jsonObject = JsonParser.parseString( idJson ).getAsJsonObject();
        int questionId = jsonObject.getAsJsonPrimitive("id").getAsInt();
        var result = quiz.getQuestionList().removeIf( question -> question.getId() == questionId );

        if( result )
        {
            LOGGER.info( String.format( "Delete question: %s success ", questionId ) );
        } else
        {
            LOGGER.info( String.format( "Delete question: %s fail ", questionId ) );
            throw new BadRequestException("Delete question: %s fail ");
        }
        return quiz.getQuestionList();
    }

    @PUT
    @Path( "{id}/questions" )
    @Consumes( MediaType.APPLICATION_JSON )
    public KahootQuestion updateQuestion(@PathParam( "id" ) String id, String payload)
    {
        var quiz = validateQuiz( Integer.parseInt( id ), "Update question" );
        var newQuestion = validateQuestion( payload );
        var oldQuestion = quiz.getQuestionList().stream().filter( question -> question.getId() == newQuestion.getId() ).findFirst().orElseGet( () -> null );
        if(oldQuestion == null)
        {
            LOGGER.info( String.format( "Edit question: %s fail, question does not exist ", quiz.getId() ) );
            throw new BadRequestException(String.format( "Edit question: %s fail, question does not exist ", quiz.getId() ));
        } else
        {
            quiz.setQuestionList( quiz.getQuestionList()
                    .stream()
                    .map( question -> question.getId() == newQuestion.getId() ? newQuestion : question  )
                    .collect( Collectors.toList()) );
            LOGGER.info( String.format( "Edit question: %s success", quiz.getId() ) );
            return newQuestion;
        }

    }

    @GET
    @Path( "{quizId}/questions/{id}")
    public KahootQuestion getQuestion(@PathParam( "quizId" ) String quizId, @PathParam( "id" ) String id)
    {
        var quiz = validateQuiz( Integer.parseInt( quizId ), "Get question" );
        var question = quiz.getQuestionList()
                .stream()
                .filter( questions1 -> questions1.getId() == Integer.parseInt( id ))
                .findFirst()
                .orElseGet( () -> null );
        if(question != null )
        {
            LOGGER.info( String.format( "Get question: %s success ", question.getId() ) );
        } else
        {
            LOGGER.info( String.format( "Get question: %s fail ", id ) );
        }
        return question;
    }
    
    private KahootQuiz validateQuiz(int id, String actionName)
    {
        var quiz = getQuizOrNull(id);
        if(quiz==null)
        {
            LOGGER.info( String.format( "%s fail, quiz: %s does not exist ", actionName, id ) );
            throw new BadRequestException( String.format( "%s fail, quiz: %s does not exist ", actionName, id ) );
        }
        return quiz;
    }
    
    private KahootQuiz getQuizOrNull(int id)
    {
        return quizzes.stream().filter( quiz1 -> quiz1.getId() == id ).findFirst().orElseGet( () -> null );
    }
    
    private KahootQuestion validateQuestion(String payload)
    {
        var jsonObject = JsonParser.parseString(payload).getAsJsonObject();
        KahootQuestionType type = KahootQuestionType.get( jsonObject.getAsJsonPrimitive("type").getAsString() );
    
        if(type!=null )
        {
            KahootQuestion question;
            switch ( type )
            {
                case STANDARD:
                    question = gson.fromJson( payload, KahootStandardQuestion.class );
                    break;
                case TRUEORFALSE:
                    question = gson.fromJson( payload, KahootTrueOrFalseQuestion.class );
                    break;
                case TYPEANSWER:
                    question = gson.fromJson( payload, KahootTypeAnswerQuestion.class );
                    break;
                default:
                    throw new BadRequestException( "Add question fail, could not identify type" );
            }
    
            question.validate();
            return question;
        } else
        {
            LOGGER.info( "Add question fail, question type missing" );
            throw new BadRequestException( "Add question fail, question type missing" );
        }
        
    }
    
}
