package sebaszczen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.apiSupplier.ApiProvider;
import sebaszczen.domain.Weather;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperService {

    @Autowired
    private ApiProvider apiProvider;

    public List<Weather> getMappedWeatherList() {
        List<Weather> weatherList = apiProvider.getAllSynopticData()
                .parallelStream().map(synoptidData -> new Weather(synoptidData)).collect(Collectors.toList());
        return weatherList;
    }
}
