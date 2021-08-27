package org.firstclass.kahoot.messages.send;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Loki
 * @date 27/08/21
 */
@Getter
@Setter
public class KahootSendMessagePayload
{
    
    protected String      errorInfo;
    protected Set<String> users;

    public KahootSendMessagePayload()
    {
        errorInfo = "";
        users = new HashSet<>();
    }
    
}
