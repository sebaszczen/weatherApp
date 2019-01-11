package sebaszczen.apiSupplier;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sebaszczen.domain.Weather;
import sebaszczen.domain.dto.gios.AirConditionDataDto;
import sebaszczen.domain.dto.gios.StationLocalizationDto;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiProvider {
    private final static String ALL_MEASURING_STATIONS_API_URL = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
    private final static String MEASURING_STATION_API_URL_BY_ID = "http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/";
    private final static String ALL_SYNOPTIC_STATIONS_API_URL = "https://danepubliczne.imgw.pl/api/data/synop";
    private final static String SYNOPTIC_STATION_BY_CITY = "https://danepubliczne.imgw.pl/api/data/synop/station/";
    private RestTemplate restTemplate = new RestTemplate();

    private URI getAllSynopticDataUri() {
        return UriComponentsBuilder.fromHttpUrl(ALL_SYNOPTIC_STATIONS_API_URL).build().encode().toUri();
    }

    private URI getSynopticDataByStationNameUri(String city) {
        return UriComponentsBuilder.fromHttpUrl(SYNOPTIC_STATION_BY_CITY).path("/" + city).build().encode().toUri();
    }

    public List<Weather.WeatherBuilder> getAllSynopticData() {
        Weather.WeatherBuilder[] forObject = restTemplate.getForObject(getAllSynopticDataUri(), Weather.WeatherBuilder[].class);
        List<Weather.WeatherBuilder> weatherList = Arrays.stream(forObject).collect(Collectors.toList());
        return weatherList;
    }

    public Weather getSynopticDataByStationName(String cityName) {
        Weather weather = restTemplate.getForObject(getSynopticDataByStationNameUri(cityName.toLowerCase()), Weather.class);
        return weather;
    }

    public List<StationLocalizationDto> getStationLocalizationsFromGiosApi() {
        StationLocalizationDto[] stationLocalizationDto = restTemplate.getForObject(ALL_MEASURING_STATIONS_API_URL, StationLocalizationDto[].class);
        List<StationLocalizationDto> collect = Arrays.stream(stationLocalizationDto).parallel().collect(Collectors.toList());
        return collect;
    }

    public List<AirConditionDataDto> getAirConditionData(){
        List<AirConditionDataDto> airConditionDataDtoList = getStationLocalizationsFromGiosApi().parallelStream()
                .map(station -> restTemplate
                        .getForObject(MEASURING_STATION_API_URL_BY_ID + station.getId(), AirConditionDataDto.class))
                .collect(Collectors.toList());

        return airConditionDataDtoList;
    }
}

