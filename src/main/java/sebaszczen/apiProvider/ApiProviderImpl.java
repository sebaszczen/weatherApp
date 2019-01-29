package sebaszczen.apiProvider;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sebaszczen.model.AirConditionData;
import sebaszczen.model.StationLocalization;
import sebaszczen.model.SynopticStation;
import sebaszczen.dto.AirConditionDataDto;
import sebaszczen.dto.StationLocalizationDto;

import java.net.URI;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ApiProviderImpl implements ApiProvider {
    private final static String ALL_MEASURING_STATIONS_API_URL = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
    private final static String MEASURING_STATION_API_URL_BY_ID = "http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/";
    private final static String ALL_SYNOPTIC_STATIONS_API_URL = "https://danepubliczne.imgw.pl/api/data/synop";
    private final static String SYNOPTIC_STATION_BY_CITY = "https://danepubliczne.imgw.pl/api/data/synop/station/";
    private RestTemplate restTemplate;
    private Logger logger = LogManager.getLogger(ApiProviderImpl.class);

    @Autowired
    public ApiProviderImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    private URI getAllSynopticDataUri() {
        return UriComponentsBuilder.fromHttpUrl(ALL_SYNOPTIC_STATIONS_API_URL).build().encode().toUri();
    }

    private URI getSynopticDataByStationNameUri(String city) {
        return UriComponentsBuilder.fromHttpUrl(SYNOPTIC_STATION_BY_CITY).path("" + city).build().encode().toUri();
    }

    private <T> T getForObject(String uri, Class<T> result) {
            return restTemplate.getForObject(uri, result);
    }

    @Override
    public List<SynopticStation> getAllSynopticStation() {
        SynopticStation.SynopticStationDto[] synopticStationDtos = getForObject(getAllSynopticDataUri().toString(), SynopticStation.SynopticStationDto[].class);
        List<SynopticStation.SynopticStationDto> synopticStationDtoList = Arrays.stream(synopticStationDtos)
                .filter(synopticStationDto -> synopticStationDto.getData_pomiaru()
                        != null&&synopticStationDto.getGodzina_pomiaru()!=null).collect(Collectors.toList());
        return synopticStationDtoList.parallelStream().map(SynopticStation.SynopticStationDto::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<SynopticStation> getSynopticDataByStationName(String cityName) {
        SynopticStation.SynopticStationDto stationDto = getForObject(getSynopticDataByStationNameUri(cityName.toLowerCase()).toString(), SynopticStation.SynopticStationDto.class);
        Optional<LocalDate> data_pomiaru = Optional.ofNullable(stationDto.getData_pomiaru());
        Optional<LocalTime> godzina_pomiaru = Optional.ofNullable(stationDto.getGodzina_pomiaru());
        if (data_pomiaru.isPresent()&&godzina_pomiaru.isPresent()){
            return Optional.of(stationDto.convertToEntity());
        }
        return Optional.empty();
    }

    @Override
    public List<StationLocalization> getStationLocalization() {
        StationLocalizationDto[] stationLocalizationDto = getForObject(ALL_MEASURING_STATIONS_API_URL, StationLocalizationDto[].class);
        List<StationLocalizationDto> stationLocalizationDtoList = Arrays.stream(stationLocalizationDto).parallel().collect(Collectors.toList());
        return stationLocalizationDtoList.parallelStream().map(StationLocalizationDto::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public List<AirConditionData> getAllAirConditionData(){
        List<AirConditionDataDto> airConditionDataDtoList = getStationLocalization().parallelStream()
                .map(station ->
                        getForObject(MEASURING_STATION_API_URL_BY_ID + station.getStationId(), AirConditionDataDto.class))
                .filter(station->station.getStCalcDate()!=null).collect(Collectors.toList());
        return airConditionDataDtoList.parallelStream().map(AirConditionDataDto::convertToEntity).collect(Collectors.toList());
    }

    public Optional<AirConditionData> getAirConditionDataByStationIndex(int index){
            AirConditionDataDto airConditionDataDto = getForObject(MEASURING_STATION_API_URL_BY_ID + index, AirConditionDataDto.class);
        Optional<LocalDateTime> stCalcDate = Optional.ofNullable(airConditionDataDto.getStCalcDate());
        if (stCalcDate.isPresent()){
        return Optional.of(airConditionDataDto.convertToEntity());
        }
        else {
            return Optional.empty();

        }
//        return airConditionDataDto.convertToEntity();
    }
}

