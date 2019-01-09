package sebaszczen.services.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.apiSupplier.ApiProvider;
import sebaszczen.repository.WeatherRepository;

@Service
public class SynopticDataServiceImpl implements SynopticDataService {

    @Autowired
    private ApiProvider apiProvider;

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public void saveWeatherMeasurements() {
//        List<Weather> allStations = apiProvider.getAllSynopticData();
//        allStations.forEach(weatherRepository::save);
    }

    @Override
    public void saveAirConditions() {

    }

}
