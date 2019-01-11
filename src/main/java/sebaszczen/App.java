package sebaszczen;

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
public class App
{

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

}
