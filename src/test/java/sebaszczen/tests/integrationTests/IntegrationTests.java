package sebaszczen.tests.integrationTests;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void saveSynopticData() {
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/api?provider=imgw", HttpMethod.POST, new HttpEntity<>(void.class), void.class);
//        String actual = responseEntity.getHeaders().get(HttpHeaders.LOCATION).get(0);
//        Assertions.assertThat(actual.contains(""))
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
