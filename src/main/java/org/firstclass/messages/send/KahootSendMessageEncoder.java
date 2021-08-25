package org.firstclass.messages.send;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author Loki
 */
public class KahootSendMessageEncoder implements Encoder.Text<KahootSendMessage>
{
    
    private static Gson gson = new Gson();
    
    @Override
    public String encode( KahootSendMessage message ) throws EncodeException
    {
        return gson.toJson( message );
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
