package sebaszczen.apiProvider;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sebaszczen.model.AirConditionData;
import sebaszczen.model.StationLocalization;
import sebaszczen.model.SynopticStation;
import sebaszczen.dto.AirConditionDataDto;
import sebaszczen.dto.StationLocalizationDto;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiProviderImpl implements ApiProvider {
    private final static String ALL_MEASURING_STATIONS_API_URL = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
    private final static String MEASURING_STATION_API_URL_BY_ID = "http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/";
    private final static String ALL_SYNOPTIC_STATIONS_API_URL = "https://danepubliczne.imgw.pl/api/data/synop";
    private final static String SYNOPTIC_STATION_BY_CITY = "https://danepubliczne.imgw.pl/api/data/synop/station/";
    private RestTemplate restTemplate = new RestTemplate();

    private URI getAllSynopticDataUri() {
        return UriComponentsBuilder.fromHttpUrl(ALL_SYNOPTIC_STATIONS_API_URL).build().encode().toUri();
    }

    private URI getSynopticDataByStationNameUri(String city) {
        return UriComponentsBuilder.fromHttpUrl(SYNOPTIC_STATION_BY_CITY).path("" + city).build().encode().toUri();
    }

    @Override
    public List<SynopticStation> getAllSynopticStation() {
        SynopticStation.SynopticStationDto[] synopticStationDtos = restTemplate.getForObject(getAllSynopticDataUri(), SynopticStation.SynopticStationDto[].class);
        List<SynopticStation.SynopticStationDto> synopticStationDtoList = Arrays.stream(synopticStationDtos).collect(Collectors.toList());
        return synopticStationDtoList.parallelStream().map(SynopticStation.SynopticStationDto::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public SynopticStation getSynopticDataByStationName(String cityName) {
        SynopticStation.SynopticStationDto stationDto = restTemplate.getForObject(getSynopticDataByStationNameUri(cityName.toLowerCase()), SynopticStation.SynopticStationDto.class);
        return stationDto.convertToEntity();
    }

    @Override
    public List<StationLocalization> getStationLocalization() {
        StationLocalizationDto[] stationLocalizationDto = restTemplate.getForObject(ALL_MEASURING_STATIONS_API_URL, StationLocalizationDto[].class);
        List<StationLocalizationDto> stationLocalizationDtoList = Arrays.stream(stationLocalizationDto).parallel().collect(Collectors.toList());
        return stationLocalizationDtoList.parallelStream().map(StationLocalizationDto::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public List<AirConditionData> getAllAirConditionData(){
        List<AirConditionDataDto> airConditionDataDtoList = getStationLocalization().parallelStream()
                .map(station -> restTemplate
                        .getForObject(MEASURING_STATION_API_URL_BY_ID + station.getStationId(), AirConditionDataDto.class))
                .collect(Collectors.toList());
        return airConditionDataDtoList.parallelStream().map(AirConditionDataDto::convertToEntity).collect(Collectors.toList());
    }

    public AirConditionData getAirConditionDataByStationIndex(int index){
        AirConditionDataDto airConditionDataDto = restTemplate.getForObject(MEASURING_STATION_API_URL_BY_ID + index, AirConditionDataDto.class);
        return airConditionDataDto.convertToEntity();
    }
}

