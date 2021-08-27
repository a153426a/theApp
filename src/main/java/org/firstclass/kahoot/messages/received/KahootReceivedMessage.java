package org.firstclass.kahoot.messages.received;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Loki
 */
@Getter
@Setter
public abstract class KahootReceivedMessage
{
    
    protected KahootReceivedMessageType type;
    protected KahootReceivedMessagePayload payload = new KahootReceivedMessagePayload();
    
}
