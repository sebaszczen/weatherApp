package sebaszczen.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import sebaszczen.apiProvider.RestTemplateResponseErrorHandler;

@org.springframework.context.annotation.Configuration
public class BeanFabric {
    Logger logger = LoggerFactory.getLogger(BeanFabric.class);

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate build = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        return build;
    }

//    @Bean
//    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
//        return new ShallowEtagHeaderFilter();
//    }

}
