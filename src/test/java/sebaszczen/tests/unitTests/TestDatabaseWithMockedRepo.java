package sebaszczen.tests.unitTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.SynopticStation;
import sebaszczen.model.AirConditionData;
import sebaszczen.repository.AirConditionDataRepository;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.repository.StationLocalizationRepository;
import sebaszczen.respository.MockAirConditionData;
import sebaszczen.respository.MockSynopticStation;
import sebaszczen.services.api.ApiService;
import sebaszczen.services.api.ApiServiceImpl;

import java.util.List;
import java.util.Optional;

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

    private MockSynopticStation mockSynopticStation =new MockSynopticStation();
    private MockAirConditionData mockAirConditionData = new MockAirConditionData();

    @Mock
    private ApiProvider apiProvider;

    private ApiService ApiService;

    @Autowired
    private ImgwApiRepository imgwApiRepository;

    @Autowired
    private AirConditionDataRepository airConditionDataRepository;

    @Autowired
    private StationLocalizationRepository stationLocalizationRepository;

    @Test
    public void saveData_AlreadyContainsData() {
        List<SynopticStation> synopticStationList = mockSynopticStation.getSynopticStationList();
        testEntityManager.persist(synopticStationList.get(0));

        when(apiProvider.getSynopticDataByStationName(anyString())).thenReturn(Optional.of(synopticStationList.get(0)));

        List<AirConditionData> mockAirConditionDataList = mockAirConditionData.getAirConditionDataList();
        testEntityManager.persist(mockAirConditionDataList.get(0));

        when(apiProvider.getAirConditionDataByStationIndex(any(int.class))).thenReturn(Optional.of(mockAirConditionDataList.get(0)));

        ApiService = new ApiServiceImpl(imgwApiRepository, apiProvider, stationLocalizationRepository, airConditionDataRepository);
        ApiService.saveData();

        verify(apiProvider,times(0)).getAllSynopticStation();
        verify(apiProvider,times(0)).getStationLocalization();
        verify(apiProvider,times(0)).getAllAirConditionData();
    }
}
