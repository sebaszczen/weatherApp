package sebaszczen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

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


    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... strings) throws Exception {
    }
}
