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
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirQuality;
import sebaszczen.repository.*;
import sebaszczen.respository.MockAirData;
import sebaszczen.respository.MockSynopticData;
import sebaszczen.services.api.ApiService;
import sebaszczen.services.api.ApiServiceImpl;
import sebaszczen.services.api.EntitiesMapperImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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

    private MockSynopticData mockSynopticData =new MockSynopticData();
    private MockAirData mockAirData = new MockAirData();

    @Mock
    private ApiProvider apiProvider;

    private ApiService ApiService;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private SynopticDataRepository synopticDataRepository;

    @Autowired
    private AirDataRepository airDataRepository;

    @Autowired
    private AirMeasurementLocalizationRepository airMeasurementLocalizationRepository;

    @Autowired
    private AirQualityRepository airQualityRepository;

    @Autowired
    private CityRepository cityRepository;

    @Mock
    private EntitiesMapperImpl entitiesMapper;

    @Test
    public void saveData_AlreadyContainsData() {
        List<SynopticData> synopticDataList = mockSynopticData.getSynopticData();
        testEntityManager.persist(synopticDataList.get(0));

        when(apiProvider.getSynopticDataByStationName(anyString())).thenReturn(Optional.of(synopticDataList.get(0)));

        List<AirData> mockAirDataList = mockAirData.getMockedAirData();
        saveEntity(mockAirDataList.get(0).getC6H6IndexAirQuality());
        saveEntity(mockAirDataList.get(0).getCoIndexAirQuality());
        saveEntity(mockAirDataList.get(0).getNo2IndexAirQuality());
        saveEntity(mockAirDataList.get(0).getO3IndexAirQuality());
        saveEntity(mockAirDataList.get(0).getPm10IndexAirQuality());
        saveEntity(mockAirDataList.get(0).getPm25IndexAirQuality());
        saveEntity(mockAirDataList.get(0).getSo2IndexAirQuality());
        saveEntity(mockAirDataList.get(0).getStIndexAirQuality());
        saveEntity(mockAirDataList.get(0));


        when(apiProvider.getAirConditionDataByStationIndex(any(int.class))).thenReturn(Optional.of(mockAirDataList.get(0)));

        ApiService = new ApiServiceImpl(synopticDataRepository, apiProvider, airDataRepository, airQualityRepository, cityRepository, entitiesMapper);
        ApiService.saveData();

        verify(apiProvider,times(0)).getAllSynopticStation();
        verify(apiProvider,times(0)).getStationLocalization();
        verify(apiProvider,times(0)).getAirData();
    }

    private <T> void saveEntity(T entity) {
        testEntityManager.merge(entity);
    }
}
