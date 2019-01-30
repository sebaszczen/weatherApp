package sebaszczen.tests.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.apiProvider.ApiProviderImpl;
import sebaszczen.apiProvider.RestTemplateResponseErrorHandler;
import sebaszczen.dto.AirConditionDataDto;
import sebaszczen.dto.StationLocalizationDto;
import sebaszczen.model.AirConditionData;
import sebaszczen.model.StationLocalization;
import sebaszczen.model.SynopticStation;
import sebaszczen.respository.MockAirConditionData;
import sebaszczen.respository.MockStationLocalization;
import sebaszczen.respository.MockSynopticStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApiProviderImplTest {

    @Mock
    private RestTemplate restTemplate = new RestTemplate();

    private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    @InjectMocks
    ApiProvider apiProvider = new ApiProviderImpl(restTemplate);

    private MockSynopticStation mockSynopticStation = new MockSynopticStation();
    private MockAirConditionData mockAirConditionData = new MockAirConditionData();

    @Test
    public void getAllSynopticStation_emptyDataAndTime() {
        SynopticStation.SynopticStationDto synopticStationDto = new SynopticStation.SynopticStationDto();
        SynopticStation.SynopticStationDto[] synopticStationDtos = {synopticStationDto};
        when(restTemplate.getForEntity( UriComponentsBuilder.fromHttpUrl("https://danepubliczne.imgw.pl/api/data/synop").build().encode().toUri().toString(),
                SynopticStation.SynopticStationDto[].class)).thenReturn(new ResponseEntity<>(synopticStationDtos,HttpStatus.OK));
        List<SynopticStation> allSynopticStation = apiProvider.getAllSynopticStation();
        assertEquals(allSynopticStation.size(),0);
    }

    @Test
    public void getAllSynopticStation() {
        List<SynopticStation.SynopticStationDto> synopticStationDtoList = mockSynopticStation.getSynopticStationDtoList();
        SynopticStation.SynopticStationDto[] synopticStationDtos1 = new SynopticStation.SynopticStationDto[synopticStationDtoList.size()];
        SynopticStation.SynopticStationDto[] synopticStationDtos = synopticStationDtoList.toArray(synopticStationDtos1);
        when(restTemplate.getForEntity( UriComponentsBuilder.fromHttpUrl("https://danepubliczne.imgw.pl/api/data/synop").build().encode().toUri().toString(),
                SynopticStation.SynopticStationDto[].class)).thenReturn(new ResponseEntity<>(synopticStationDtos,HttpStatus.OK));
        List<SynopticStation> allSynopticStation = apiProvider.getAllSynopticStation();
        assertEquals(allSynopticStation.size(),synopticStationDtos.length);
    }

    @Test
    public void getSynopticDataByStationName() {
        String stattionName = "warszawa";
        SynopticStation.SynopticStationDto synopticStationDto = mockSynopticStation.getSynopticStationDtoList().get(0);
        when(restTemplate.getForEntity( UriComponentsBuilder.fromHttpUrl("https://danepubliczne.imgw.pl/api/data/synop/station/").path("" + stattionName).build().encode().toUri().toString(),
                SynopticStation.SynopticStationDto.class)).thenReturn(new ResponseEntity<>(synopticStationDto,HttpStatus.OK));
        Optional<SynopticStation> synopticDataByStationName = apiProvider.getSynopticDataByStationName(stattionName);
        assertEquals(synopticDataByStationName.isPresent(),true);
    }

    @Test
    public void getSynopticDataByStationName_emptyData() {
        String stattionName = "warszawa";
        SynopticStation.SynopticStationDto synopticStationDto=new SynopticStation.SynopticStationDto();
        when(restTemplate.getForEntity( UriComponentsBuilder.fromHttpUrl("https://danepubliczne.imgw.pl/api/data/synop/station/").path("" + stattionName).build().encode().toUri().toString(),
                SynopticStation.SynopticStationDto.class)).thenReturn(new ResponseEntity<>(synopticStationDto,HttpStatus.OK));
        Optional<SynopticStation> synopticDataByStationName = apiProvider.getSynopticDataByStationName(stattionName);
        assertEquals(synopticDataByStationName.isPresent(),false);
    }

    @Test
    public void getStationLocalization() {
    }

    @Test
    public void getAllAirConditionData_noDate() {
        StationLocalizationDto stationLocalizationDto = new StationLocalizationDto();
        stationLocalizationDto.setStationId(114);
        StationLocalizationDto stationLocalizationDto2 = new StationLocalizationDto();
        stationLocalizationDto2.setStationId(113);
        StationLocalizationDto[] stationLocalizationDtos = {stationLocalizationDto,stationLocalizationDto2};

        when(restTemplate.getForEntity("http://api.gios.gov.pl/pjp-api/rest/station/findAll", StationLocalizationDto[].class))
                .thenReturn(new ResponseEntity<>(stationLocalizationDtos, HttpStatus.OK));

        AirConditionDataDto airConditionDataDto1=new AirConditionDataDto();
        airConditionDataDto1.setStCalcDate("2018-12-03 12:22");
        AirConditionDataDto airConditionDataDto2 = new AirConditionDataDto();
        when(restTemplate.getForEntity("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/114",
                AirConditionDataDto.class)).thenReturn(new ResponseEntity<>(airConditionDataDto1,HttpStatus.OK));
        when(restTemplate.getForEntity("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/113",
                AirConditionDataDto.class)).thenReturn(new ResponseEntity<>(airConditionDataDto2,HttpStatus.OK));
        List<AirConditionData> allAirConditionData = apiProvider.getAllAirConditionData();
        assertEquals(allAirConditionData.size(),1);
    }

    @Test
    public void getAirConditionDataByStationIndex() {
        AirConditionDataDto airConditionDataDto = mockAirConditionData.getMockAirConditionDataDtoList().get(0);
        when(restTemplate.getForEntity("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/114", AirConditionDataDto.class))
        .thenReturn(new ResponseEntity<>(airConditionDataDto, HttpStatus.OK) );
        Optional<AirConditionData> airConditionDataByStationIndex = apiProvider.getAirConditionDataByStationIndex(114);
        assertEquals(airConditionDataByStationIndex.isPresent(),true);
    }

    @Test
    public void getAirConditionDataByStationIndex_NoData() {
        AirConditionDataDto airConditionDataDto = new AirConditionDataDto();
        when(restTemplate.getForEntity("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/114", AirConditionDataDto.class))
                .thenReturn(new ResponseEntity<>(airConditionDataDto,HttpStatus.OK));
        Optional<AirConditionData> airConditionDataByStationIndex = apiProvider.getAirConditionDataByStationIndex(114);
        assertEquals(airConditionDataByStationIndex.isPresent(),false);
    }
}