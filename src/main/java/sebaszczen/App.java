package sebaszczen;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.services.api.ApiService;

import java.util.logging.Logger;

/**
 * Hello world!
 *
 */

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class App implements CommandLineRunner
{
    private static Logger logger = Logger.getLogger(App.class.getName());
//    @Autowired
//    private ApiProvider apiProvider;

    @Autowired
    private ApiService apiService;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
        logger.info("watek:  "+ Thread.currentThread().getName());
    }

    @Override
    public void run(String... strings) throws Exception {
//        apiProvider.getAirConditionData();
//        apiProvider.getAirConditionData();
//        apiService.saveData();
    }
}
