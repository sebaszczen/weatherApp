package sebaszczen.tests.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.scheduling.annotation.AsyncResult;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.City;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirQuality;
import sebaszczen.repository.*;
import sebaszczen.respository.*;
import sebaszczen.services.api.ApiServiceImpl;
import sebaszczen.services.api.EntitiesMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiServiceImplTest {

    @Mock
    private SynopticDataRepository synopticDataRepository;

    @Mock
    private AirDataRepository airDataRepository;

    @Mock
    private ApiProvider apiProvider;

    @Mock
    private AirQualityRepository airQualityRepository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private EntitiesMapper entitiesMapper;

    @InjectMocks
    private ApiServiceImpl apiServiceImpl;

    private MockSynopticData mockSynopticData =new MockSynopticData();
    private MockAirData mockAirData = new MockAirData();
    private MockCityToAirDataMap mockCityToAirDataMap = new MockCityToAirDataMap();
    private MockCityToSynopticDataMap mockCityToSynopticDataMap = new MockCityToSynopticDataMap();
    private MockCity mockCity = new MockCity();

    @Test
    public void saveData_getDataSaved() throws ExecutionException, InterruptedException {
        List<SynopticData> synopticDataList = mockSynopticData.getSynopticData();
        List<AirData> mockAirDataDtoList = mockAirData.getMockedAirData();
        Future<Map<String, List<AirData>>> cityToAirDataMock = new AsyncResult<>(mockCityToAirDataMap.getCityToAirDataMock());
        Future<Map<String, List<SynopticData>>> cityToSynopticDataMock = new AsyncResult<>(mockCityToSynopticDataMap.getCityToSynopticDataMock());
        List<City> mockedCity = mockCity.getMockedCity();

        when(entitiesMapper.mapCityToSynopticData()).thenReturn(cityToSynopticDataMock);
        when(entitiesMapper.mapCityToAirData()).thenReturn(cityToAirDataMock);
        when(apiProvider.getSynopticDataByStationName(anyString())).thenReturn(Optional.of(synopticDataList.get(0)));
        when(apiProvider.getAirConditionDataByStationIndex(any(int.class))).thenReturn(Optional.of(mockAirDataDtoList.get(0)));
        when(synopticDataRepository.save(any(SynopticData.class))).thenReturn(synopticDataList.get(0));
        when(synopticDataRepository.contain(any(int.class), any(int.class), any(int.class),any(int.class))).thenReturn(0);
        when(airDataRepository.contain(any(int.class), any(int.class),any(int.class), any(int.class))).thenReturn(0);
        when(cityRepository.existsAllByName(any(String.class))).thenReturn(false);
        when(cityRepository.save(any(City.class))).thenReturn(mockedCity.get(0));
        when(entitiesMapper.injectLocalizationToAirData()).thenReturn(mockAirDataDtoList);
        when(airQualityRepository.save(any(AirQuality.class))).thenReturn(mockAirDataDtoList.get(0).getC6H6IndexAirQuality());

        apiServiceImpl.saveData();

        verify(cityRepository,times(cityToAirDataMock.get().size()+cityToSynopticDataMock.get().size())).save(any(City.class));
//        verify(airDataRepository,times(2)).save(any(AirData.class));

    }
}
