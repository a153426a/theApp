package org.firstclass.messages.received;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @author Loki
 */
public class KahootReceivedMessageDecoder implements Decoder.Text<KahootReceivedMessage>
{
    private JsonObject jsonObject;
    
    private static final Gson gson = new Gson();
    
    @Override
    public KahootReceivedMessage decode( String s )
    {
        KahootReceivedMessageType type = KahootReceivedMessageType.get( jsonObject.getAsJsonPrimitive("type").getAsString() );
        if(type != null )
        {
            switch ( type )
            {
                case SELECT_ANSWER:
                    var message = new SelectAnswerReceivedMessage();
                    message.setSelection( KahootSelections.get( jsonObject.getAsJsonPrimitive("selection").getAsString() ) );
                    return message;
                case OTHER:
                    return gson.fromJson( s, OtherReceivedMessage.class);
                default:
                    break;
            }
        }
        return null;
    }
    
    @Override
    public boolean willDecode( String s )
    {
        try
        {
            jsonObject = JsonParser.parseString(s).getAsJsonObject();
            return jsonObject.isJsonObject();
        } catch ( IllegalStateException e )
        {
            return false;
        }
        
    }
    
    @Override
    public void init( EndpointConfig endpointConfig )
    {
    
    }
    
    @Override
    public void destroy()
    {
    
    }
}
