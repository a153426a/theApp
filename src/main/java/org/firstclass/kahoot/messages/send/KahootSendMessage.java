package org.firstclass.kahoot.messages.send;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Loki
 */
@Getter
@Setter
public abstract class KahootSendMessage
{
    
    protected KahootSendMessageType type;
    protected KahootSendMessagePayload payload = new KahootSendMessagePayload();
    
}
