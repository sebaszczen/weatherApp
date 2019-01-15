package sebaszczen;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sebaszczen.apiProvider.ApiProvider;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App implements CommandLineRunner
{

    @Autowired
    private ApiProvider apiProvider;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        apiProvider.getStationLocalizationsFromGiosApi();
//        apiProvider.getAirConditionData();
    }
}
