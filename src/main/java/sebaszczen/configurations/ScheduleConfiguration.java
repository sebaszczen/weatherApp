package sebaszczen.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
public class ScheduleConfiguration implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskScheduler());
    }

    @Bean
    public Executor taskScheduler() {
        ThreadPoolTaskScheduler t = new ThreadPoolTaskScheduler();
        t.setPoolSize(2);
        t.setErrorHandler(x-> System.out.println("tuteeeeeej"));
        t.setThreadNamePrefix("taskScheduler - ");
        t.initialize();
        return t;
    }
}
