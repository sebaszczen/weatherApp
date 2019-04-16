package sebaszczen.apiProvider;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    org.slf4j.Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException, UnknownHostException {

            return (httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode()
                .series() == SERVER_ERROR) {
            logger.error("Server error: ",httpResponse.getStatusCode(),httpResponse.getStatusText());
            // handle SERVER_ERROR
        } else if (httpResponse.getStatusCode()
                .series() == CLIENT_ERROR) {
            logger.error("Client error: ",httpResponse.getStatusCode(),httpResponse.getStatusText());

            // handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.error("Api connection error: "+httpResponse.getStatusCode().toString()+" "+ httpResponse.getStatusText());
                throw new IOException();
            }
        }
    }

}
