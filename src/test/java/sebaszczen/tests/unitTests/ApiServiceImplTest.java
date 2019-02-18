package sebaszczen.tests.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirMeasurementLocalization;
import sebaszczen.repository.*;
import sebaszczen.respository.MockAirConditionData;
import sebaszczen.respository.MockStationLocalization;
import sebaszczen.respository.MockSynopticStation;
import sebaszczen.services.api.ApiServiceImpl;
import sebaszczen.services.api.EntitiesMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private MockSynopticStation mockSynopticStation =new MockSynopticStation();
    private MockAirConditionData mockAirConditionData = new MockAirConditionData();

    @Test
    public void saveData_getDataSaved() {
        List<SynopticData> synopticDataList = mockSynopticStation.getSynopticData();
        List<AirData> mockAirDataDtoList = mockAirConditionData.getAirData();
        Map map=new HashMap()

        when(entitiesMapper.mapCityToSynopticData()).thenReturn();
        when(entitiesMapper.mapCityToAirData()).thenReturn()
        when(apiProvider.getSynopticDataByStationName(anyString())).thenReturn(Optional.of(synopticDataList.get(0)));
        when(apiProvider.getAirConditionDataByStationIndex(any(int.class))).thenReturn(Optional.of(mockAirDataDtoList.get(0)));

//        when(synopticDataRepository.save(any(SynopticData.class))).thenReturn(synopticDataList.get(0));
//        when(airMeasurementLocalizationRepository.save(any(AirMeasurementLocalization.class))).thenReturn(mockAirMeasurementLocalizationDtoList.get(0));
//        when(airDataRepository.save(any(AirData.class))).thenReturn(mockAirDataDtoList.get(0));

        apiServiceImpl.saveData();

        verify(synopticDataRepository,times(2)).save(any(SynopticData.class));
        verify(airDataRepository,times(2)).save(any(AirData.class));

    }
}
