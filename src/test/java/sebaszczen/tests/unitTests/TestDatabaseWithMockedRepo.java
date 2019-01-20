package sebaszczen.tests.unitTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.domain.SynopticStation;
import sebaszczen.domain.gios.AirConditionData;
import sebaszczen.domain.gios.StationLocalization;
import sebaszczen.domain.gios.dto.AirConditionDataDto;
import sebaszczen.repository.AirConditionDataRepository;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.repository.StationLocalizationRepository;
import sebaszczen.respository.MockAirConditionDataDto;
import sebaszczen.respository.MockSynopticStationDto;
import sebaszczen.services.api.ApiService;
import sebaszczen.services.api.ApiServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE )
public class TestDatabaseWithMockedRepo {

    @Rule
    public MockitoRule mockitoRule;

    @Autowired
    private TestEntityManager testEntityManager;

    private MockSynopticStationDto mockSynopticStationDto=new MockSynopticStationDto();
    private MockAirConditionDataDto mockAirConditionDataDto = new MockAirConditionDataDto();

    @Mock
    private ApiProvider apiProvider;

    private ApiService apiService;

    @Autowired
    private ImgwApiRepository imgwApiRepository;

    @Autowired
    private AirConditionDataRepository airConditionDataRepository;

    @Autowired
    private StationLocalizationRepository stationLocalizationRepository;

    @Test
    public void saveData_AlreadyContainsData() {
        List<SynopticStation.SynopticStationDto> synopticStationDtoList = mockSynopticStationDto.getSynopticStationDtoList();
        List<SynopticStation> synopticStationList = synopticStationDtoList.parallelStream().map(SynopticStation::new).collect(Collectors.toList());
        testEntityManager.persist(synopticStationList.get(0));

        when(apiProvider.getSynopticDataByStationName(anyString())).thenReturn(synopticStationDtoList.get(0));

        List<AirConditionDataDto> mockAirConditionDataDtoList = mockAirConditionDataDto.getMockAirConditionDataDtoList();
        List<AirConditionData> airConditionDataList = mockAirConditionDataDtoList.parallelStream().map(AirConditionData::new).collect(Collectors.toList());
        testEntityManager.persist(airConditionDataList.get(0));

        when(apiProvider.getAirConditionDataByStationIndex(any(int.class))).thenReturn(mockAirConditionDataDtoList.get(0));

        apiService = new ApiServiceImpl(imgwApiRepository, apiProvider, stationLocalizationRepository, airConditionDataRepository);
        apiService.saveData();

        verify(apiProvider,times(0)).getAllSynopticStationDto();
        verify(apiProvider,times(0)).getStationLocalizationDto();
        verify(apiProvider,times(0)).getAllAirConditionDataDto();
    }
}
