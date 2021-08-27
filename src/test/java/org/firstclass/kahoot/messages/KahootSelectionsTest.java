package org.firstclass.kahoot.messages;

import org.firstclass.kahoot.messages.received.KahootSelections;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Loki
 */
class KahootSelectionsTest
{
    @Test
    void getTest()
    {
        var result = KahootSelections.get("ONE");
        
        assertThat( result ).isEqualTo( KahootSelections.ONE );
    }
}
