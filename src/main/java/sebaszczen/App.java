package sebaszczen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import sebaszczen.repository.AirDataRepository;

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
//
//    @Autowired
//    private ModelMapper modelMapper;

//    @Autowired
//    private ApiService apiService;

    @Autowired
    AirDataRepository airDataRepository;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... strings) throws Exception {
//        AirData airConditionDataByStationIndex = apiProvider.getAirConditionDataByStationIndex(114);
//        AirData map = modelMapper.map(airConditionDataByStationIndex, AirData.class);

//        apiProvider.getSynopticDataByStationName("warszawa");
//        Thread.sleep(40000);
//        airDataRepository.delete(12l);
    }
}
