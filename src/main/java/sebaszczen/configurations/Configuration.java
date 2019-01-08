package sebaszczen.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@org.springframework.context.annotation.Configuration
public class Configuration {

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


}
