package sebaszczen;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.apiProvider.ApiProviderImpl;
import sebaszczen.dto.AirConditionDataDto;
import sebaszczen.model.AirConditionData;
import sebaszczen.model.SynopticStation;

import javax.sql.rowset.spi.SyncProvider;
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
//    private IApiService apiService;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... strings) throws Exception {
//        AirConditionData airConditionDataByStationIndex = apiProvider.getAirConditionDataByStationIndex(114);
//        AirConditionData map = modelMapper.map(airConditionDataByStationIndex, AirConditionData.class);

//        apiProvider.getSynopticDataByStationName("warszawa");
    }
}
