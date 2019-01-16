package sebaszczen.apiProvider;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sebaszczen.domain.SynopticStation;
import sebaszczen.domain.gios.dto.AirConditionDataDto;
import sebaszczen.domain.gios.dto.StationLocalizationDto;

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

    public List<SynopticStation.SynopticStationDto> getAllSynopticStationDto() {
        SynopticStation.SynopticStationDto[] forObject = restTemplate.getForObject(getAllSynopticDataUri(), SynopticStation.SynopticStationDto[].class);
        return Arrays.stream(forObject).collect(Collectors.toList());
    }

    public SynopticStation getSynopticDataByStationName(String cityName) {
        return restTemplate.getForObject(getSynopticDataByStationNameUri(cityName.toLowerCase()), SynopticStation.class);
    }

    public List<StationLocalizationDto> getStationLocalizationDto() {
        StationLocalizationDto[] stationLocalizationDto = restTemplate.getForObject(ALL_MEASURING_STATIONS_API_URL, StationLocalizationDto[].class);
        return Arrays.stream(stationLocalizationDto).parallel().collect(Collectors.toList());
    }

    public List<AirConditionDataDto> getAirConditionData(){

        return getStationLocalizationDto().parallelStream()
                .map(station -> restTemplate
                        .getForObject(MEASURING_STATION_API_URL_BY_ID + station.getStationId(), AirConditionDataDto.class))
                .collect(Collectors.toList());
    }
}

