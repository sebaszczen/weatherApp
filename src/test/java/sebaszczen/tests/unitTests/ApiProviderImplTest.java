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
import sebaszczen.dto.AirDataDto;
import sebaszczen.dto.StationLocalizationDto;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.SynopticData;
import sebaszczen.respository.MockAirData;
import sebaszczen.respository.MockSynopticData;

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

    private MockSynopticData mockSynopticData = new MockSynopticData();
    private MockAirData mockAirData = new MockAirData();

    @Test
    public void getAllSynopticStation_emptyDataAndTime() {
        SynopticData.SynopticStationDto synopticStationDto = new SynopticData.SynopticStationDto();
        SynopticData.SynopticStationDto[] synopticStationDtos = {synopticStationDto};
        when(restTemplate.getForEntity( UriComponentsBuilder.fromHttpUrl("https://danepubliczne.imgw.pl/api/data/synop").build().encode().toUri().toString(),
                SynopticData.SynopticStationDto[].class)).thenReturn(new ResponseEntity<>(synopticStationDtos,HttpStatus.OK));
        List<SynopticData> allSynopticData = apiProvider.getAllSynopticStation();
        assertEquals(allSynopticData.size(),0);
    }

    @Test
    public void getAllSynopticStation() {
        List<SynopticData.SynopticStationDto> synopticStationDtoList = mockSynopticData.getSynopticStationDto();
        SynopticData.SynopticStationDto[] synopticStationDtos1 = new SynopticData.SynopticStationDto[synopticStationDtoList.size()];
        SynopticData.SynopticStationDto[] synopticStationDtos = synopticStationDtoList.toArray(synopticStationDtos1);
        when(restTemplate.getForEntity( UriComponentsBuilder.fromHttpUrl("https://danepubliczne.imgw.pl/api/data/synop").build().encode().toUri().toString(),
                SynopticData.SynopticStationDto[].class)).thenReturn(new ResponseEntity<>(synopticStationDtos,HttpStatus.OK));
        List<SynopticData> allSynopticData = apiProvider.getAllSynopticStation();
        assertEquals(allSynopticData.size(),synopticStationDtos.length);
    }

    @Test
    public void getSynopticDataByStationName() {
        String stattionName = "warszawa";
        SynopticData.SynopticStationDto synopticStationDto = mockSynopticData.getSynopticStationDto().get(0);
        when(restTemplate.getForEntity( UriComponentsBuilder.fromHttpUrl("https://danepubliczne.imgw.pl/api/data/synop/station/").path("" + stattionName).build().encode().toUri().toString(),
                SynopticData.SynopticStationDto.class)).thenReturn(new ResponseEntity<>(synopticStationDto,HttpStatus.OK));
        Optional<SynopticData> synopticDataByStationName = apiProvider.getSynopticDataByStationName(stattionName);
        assertEquals(synopticDataByStationName.isPresent(),true);
    }

    @Test
    public void getSynopticDataByStationName_emptyData() {
        String stattionName = "warszawa";
        SynopticData.SynopticStationDto synopticStationDto=new SynopticData.SynopticStationDto();
        when(restTemplate.getForEntity( UriComponentsBuilder.fromHttpUrl("https://danepubliczne.imgw.pl/api/data/synop/station/").path("" + stattionName).build().encode().toUri().toString(),
                SynopticData.SynopticStationDto.class)).thenReturn(new ResponseEntity<>(synopticStationDto,HttpStatus.OK));
        Optional<SynopticData> synopticDataByStationName = apiProvider.getSynopticDataByStationName(stattionName);
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

        AirDataDto airDataDto1 =new AirDataDto();
        airDataDto1.setStCalcDate("2018-12-03 12:22");
        AirDataDto airDataDto2 = new AirDataDto();
        when(restTemplate.getForEntity("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/114",
                AirDataDto.class)).thenReturn(new ResponseEntity<>(airDataDto1,HttpStatus.OK));
        when(restTemplate.getForEntity("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/113",
                AirDataDto.class)).thenReturn(new ResponseEntity<>(airDataDto2,HttpStatus.OK));
        List<AirData> allAirData = apiProvider.getAirData();
        assertEquals(allAirData.size(),1);
    }

    @Test
    public void getAirConditionDataByStationIndex() {
        AirDataDto airDataDto = mockAirData.getMockedAirDataDto().get(0);
        when(restTemplate.getForEntity("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/114", AirDataDto.class))
        .thenReturn(new ResponseEntity<>(airDataDto, HttpStatus.OK) );
        Optional<AirData> airConditionDataByStationIndex = apiProvider.getAirConditionDataByStationIndex(114);
        assertEquals(airConditionDataByStationIndex.isPresent(),true);
    }

    @Test
    public void getAirConditionDataByStationIndex_NoData() {
        AirDataDto airDataDto = new AirDataDto();
        when(restTemplate.getForEntity("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/114", AirDataDto.class))
                .thenReturn(new ResponseEntity<>(airDataDto,HttpStatus.OK));
        Optional<AirData> airConditionDataByStationIndex = apiProvider.getAirConditionDataByStationIndex(114);
        assertEquals(airConditionDataByStationIndex.isPresent(),false);
    }
}