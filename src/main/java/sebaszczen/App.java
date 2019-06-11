package sebaszczen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * Hello world!
 *
 */


//@EnableCaching
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class App implements CommandLineRunner
{

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... strings) throws Exception {

    }

}

