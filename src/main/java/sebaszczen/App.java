package sebaszczen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sebaszczen.repository.CityRepository;

import java.util.logging.Logger;

/**
 * Hello world!
 *
 */


//@EnableCaching
//@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class App implements CommandLineRunner
{
    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... strings) throws Exception {
    }

}


