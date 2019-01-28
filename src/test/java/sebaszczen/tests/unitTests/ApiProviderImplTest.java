package sebaszczen.tests.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.apiProvider.ApiProviderImpl;
import sebaszczen.model.SynopticStation;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApiProviderImplTest {

    @Mock
    private RestTemplate restTemplate = new RestTemplate();

    @InjectMocks
    ApiProvider apiProvider = new ApiProviderImpl(restTemplate);

    @Test
    public void getAllSynopticStation() {
        SynopticStation.SynopticStationDto synopticStationDto = new SynopticStation.SynopticStationDto();
        SynopticStation.SynopticStationDto[] synopticStationDtos = {synopticStationDto};
        when(restTemplate.getForObject( UriComponentsBuilder.fromHttpUrl("https://danepubliczne.imgw.pl/api/data/synop").build().encode().toUri(),
                SynopticStation.SynopticStationDto[].class)).thenReturn(synopticStationDtos);
        List<SynopticStation> allSynopticStation = apiProvider.getAllSynopticStation();
        assertEquals(allSynopticStation.size(),0);
    }

    @Test
    public void getSynopticDataByStationName() {
    }

    @Test
    public void getStationLocalization() {
    }

    @Test
    public void getAllAirConditionData() {
    }

    @Test
    public void getAirConditionDataByStationIndex() {
    }
}