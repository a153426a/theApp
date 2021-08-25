package org.firstclass.messages.received;

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
    
}
