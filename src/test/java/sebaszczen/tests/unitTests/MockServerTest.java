package sebaszczen.tests.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.apiProvider.ApiProviderImpl;
import sebaszczen.apiProvider.RestTemplateResponseErrorHandler;
import sebaszczen.configurations.Configuration;
import sebaszczen.exception.NotFoundException;
import sebaszczen.model.StationLocalization;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Configuration.class, ApiProviderImpl.class })
@RestClientTest
public class MockServerTest {

    @Autowired
    private MockRestServiceServer server;

//    @Autowired
//    private RestTemplateBuilder builder;

    @Autowired
    @Qualifier("restbean")
    private RestTemplate restTemplate;

    @Autowired
    private ApiProviderImpl apiProvider;

    @Test(expected = NotFoundException.class)
    public void  givenRemoteApiCall_when404Error_thenThrowNotFound() {
        server
                .expect(ExpectedCount.once(), requestTo("https://danepubliczne.imgw.pl/api/data/synop"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));
        apiProvider.getAllSynopticStation();
        this.server.verify();
    }
}
