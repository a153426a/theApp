package org.firstclass.kahoot.messages;

import org.firstclass.kahoot.messages.received.KahootReceivedMessageType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Loki
 */
class KahootReceivedMessageTypeTest
{
    @Test
    void getTest()
    {
        var result = KahootReceivedMessageType.get("SELECT_ANSWER");
        
        assertThat( result ).isEqualTo( KahootReceivedMessageType.SELECT_ANSWER );
    }
}
