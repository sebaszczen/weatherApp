package sebaszczen.services.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.apiSupplier.ApiProvider;
import sebaszczen.domain.Weather;
import sebaszczen.repository.WeatherRepository;

import java.util.List;

@Service
public class ApiSupplierImpl implements ApiSupplier {

    @Autowired
    private ApiProvider apiProvider;

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public void saveWeatherMeasurements() {
        List<Weather> allStations = apiProvider.getAllStations();
        allStations.forEach(weatherRepository::save);
    }
}
