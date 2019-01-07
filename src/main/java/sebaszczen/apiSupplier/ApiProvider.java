package sebaszczen.apiSupplier;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sebaszczen.domain.Weather;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiProvider {
    private final static String ALL_MEASURING_STATIONS_API_URL = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
    private final static String MEASURING_STATION_API_URL_BY_ID = "http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/";
    public final static String ALL_SYNOPTIC_STATIONS_API_URL = "https://danepubliczne.imgw.pl/api/data/synop";
    public final static String SYNOPTIC_STATION_BY_CITY = "https://danepubliczne.imgw.pl/api/data/synop/station/";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Weather> getAllStations() {
        ResponseEntity<Weather[]> responseEntity = restTemplate.getForEntity(ALL_SYNOPTIC_STATIONS_API_URL, Weather[].class);
        Weather[] body = responseEntity.getBody();
        List<Weather> weatherList = Arrays.stream(body).collect(Collectors.toList());
        return weatherList;
    }

    public Weather getOneStationById() {
        ResponseEntity<Weather> responseEntity = restTemplate.getForEntity(MEASURING_STATION_API_URL_BY_ID, Weather.class);
        Weather weather = responseEntity.getBody();
        return weather;
    }



}
