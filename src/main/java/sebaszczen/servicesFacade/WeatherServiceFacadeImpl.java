package sebaszczen.servicesFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.dto.AirDataDto;
import sebaszczen.dto.CityDto;
import sebaszczen.services.CityService;
import sebaszczen.services.DataService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherServiceFacadeImpl implements WeatherServiceFacade {

    private DataService dataService;
    private CityService cityService;

    @Autowired
    public WeatherServiceFacadeImpl(DataService dataService, CityService cityService) {
        this.dataService = dataService;
        this.cityService = cityService;
    }

    @Override
    public CityDto findLastDataForCityName(String cityName) {
        return dataService.findLastDataForCityName(cityName);
    }

    @Override
    public List<CityDto> findAllCities() {
        List<CityDto> cityDtoList = cityService.findAll().stream().map(CityDto::new).collect(Collectors.toList());
        return cityDtoList;
    }

    @Override
    public List<AirDataDto> findAirDataForCity(String name) {
        return dataService.getLastAirDataforCity(name).get().stream().map(AirDataDto::new).collect(Collectors.toList());
    }

}
