package sebaszczen.apiProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sebaszczen.dto.AirDataDto;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.airModel.AirMeasurementLocalization;
import sebaszczen.model.synopticModel.SynopticData;
import sebaszczen.dto.StationLocalizationDto;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ApiProviderImpl implements ApiProvider {
    private final static String ALL_MEASURING_STATIONS_API_URL = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
    private final static String MEASURING_STATION_API_URL_BY_ID = "http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/";
    private final static String ALL_SYNOPTIC_STATIONS_API_URL = "https://danepubliczne.imgw.pl/api/data/synop";
    private final static String SYNOPTIC_STATION_BY_CITY = "https://danepubliczne.imgw.pl/api/data/synop/station/";
    private RestTemplate restTemplate;


    @Autowired
    public ApiProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private URI getAllSynopticDataUri() {
        return UriComponentsBuilder.fromHttpUrl(ALL_SYNOPTIC_STATIONS_API_URL).build().encode().toUri();
    }

    private URI getSynopticDataByStationNameUri(String city) {
        return UriComponentsBuilder.fromHttpUrl(SYNOPTIC_STATION_BY_CITY).path("" + city).build().encode().toUri();
    }

    private <T> ResponseEntity<T> getForObject(String uri, Class<T> result) {
            return restTemplate.getForEntity(uri, result);
    }

    @Override
    public List<SynopticData> getAllSynopticStation() throws RestClientException {
        ResponseEntity<SynopticData.SynoptiDataDto[]> responseEntity = getForObject(getAllSynopticDataUri().toString(), SynopticData.SynoptiDataDto[].class);
        SynopticData.SynoptiDataDto[] synoptiDataDtos = responseEntity.getBody();
        List<SynopticData.SynoptiDataDto> synoptiDataDtoList = Arrays.stream(synoptiDataDtos).parallel()
                .filter(synopticStationDto -> synopticStationDto.getData_pomiaru()
                        != null&&synopticStationDto.getGodzina_pomiaru()!=null).collect(Collectors.toList());
        return synoptiDataDtoList.parallelStream().map(SynopticData.SynoptiDataDto::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<SynopticData> getSynopticDataByStationName(String cityName) throws ResourceAccessException {
        ResponseEntity<SynopticData.SynoptiDataDto> responseEntity = getForObject(getSynopticDataByStationNameUri(cityName.toLowerCase()).toString(), SynopticData.SynoptiDataDto.class);
        SynopticData.SynoptiDataDto stationDto = responseEntity.getBody();
        Optional<LocalDate> data_pomiaru = Optional.ofNullable(stationDto.getData_pomiaru());
        Optional<LocalTime> godzina_pomiaru = Optional.ofNullable(stationDto.getGodzina_pomiaru());
        if (data_pomiaru.isPresent()&&godzina_pomiaru.isPresent()){
            return Optional.of(stationDto.convertToEntity());
        }
        return Optional.empty();
    }

    @Override
    public Map<Integer, AirMeasurementLocalization> getStationLocalization() {
        ResponseEntity<StationLocalizationDto[]> responseEntity = getForObject(ALL_MEASURING_STATIONS_API_URL, StationLocalizationDto[].class);
        StationLocalizationDto[] stationLocalizationDtos = responseEntity.getBody();
        List<StationLocalizationDto> stationLocalizationDtoList = Arrays.stream(stationLocalizationDtos).parallel().collect(Collectors.toList());

        return stationLocalizationDtoList.parallelStream().filter(stationLocalizationDto -> stationLocalizationDto.getAirCityDto()!=null).map(StationLocalizationDto::convertToEntity).collect(Collectors.toMap(AirMeasurementLocalization::getStationId,y->y));
    }

    @Override
    public List<AirData> getAirData() {
        Map<Integer, AirMeasurementLocalization> stationLocalization = getStationLocalization();

        List<AirDataDto> airDataDtoList = stationLocalization.
                values().parallelStream()
                .map(station ->
                        getForObject(MEASURING_STATION_API_URL_BY_ID + station.getStationId(), AirDataDto.class).getBody())
                .filter(station->station.getStCalcDate()!=null).collect(Collectors.toList());
        return airDataDtoList.parallelStream().map(AirDataDto::convertToEntity).collect(Collectors.toList());
    }

    public Optional<AirData> getAirConditionDataByStationIndex(int index){
        ResponseEntity<AirDataDto> responseEntity = getForObject(MEASURING_STATION_API_URL_BY_ID + index, AirDataDto.class);
        AirDataDto airDataDto = responseEntity.getBody();
        Optional<LocalDateTime> stCalcDate = Optional.ofNullable(airDataDto.getStCalcDate());
        if (stCalcDate.isPresent()){
        return Optional.of(airDataDto.convertToEntity());
        }
        else {
            return Optional.empty();

        }
    }
}

