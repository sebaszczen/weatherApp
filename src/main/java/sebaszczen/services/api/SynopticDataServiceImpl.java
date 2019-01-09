package sebaszczen.services.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.domain.Weather;
import sebaszczen.repository.WeatherRepository;
import sebaszczen.services.MapperService;

import java.util.List;

@Service
public class SynopticDataServiceImpl implements SynopticDataService {

    @Autowired
    private MapperService mapperService;

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public void saveSynopticData() {
        List<Weather> mappedWeatherList = mapperService.getMappedWeatherList();
        mappedWeatherList.forEach(weatherRepository::save);
    }

    @Override
    public void saveAirConditions() {

    }
}
