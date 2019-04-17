package sebaszczen.servicesFacade;

import sebaszczen.dto.AirDataDto;
import sebaszczen.dto.CityDto;

import java.util.List;

public interface WeatherServiceFacade {

    CityDto findLastDataForCityName(String name);

    List<CityDto> findAllCities();

    List<AirDataDto> findAllAirDataForCity(String name);

}
