package sebaszczen.configurations;

import org.apache.logging.log4j.LogManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import sebaszczen.apiProvider.RestTemplateResponseErrorHandler;

import java.util.concurrent.Executor;

@org.springframework.context.annotation.Configuration
public class Configuration {
    org.apache.logging.log4j.Logger logger = LogManager.getLogger(Configuration.class);


    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors()*5);
        executor.setMaxPoolSize(80);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Thread-> ");
        executor.initialize();
        return executor;
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

}
