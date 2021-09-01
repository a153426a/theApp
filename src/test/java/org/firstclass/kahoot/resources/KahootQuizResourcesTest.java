package org.firstclass.kahoot.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Loki
 * @date 1/09/21
 */
@QuarkusTest
class KahootQuizResourcesTest
{

    @Test
    void initTest()
    {
        var resources = new KahootQuizResources();
        
        assertThat( resources.getQuizzes().size() ).isEqualTo( 3 );
    }

}
