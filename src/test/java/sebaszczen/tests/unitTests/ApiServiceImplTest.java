package sebaszczen.tests.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.airModel.AirData;
import sebaszczen.model.SynopticData;
import sebaszczen.model.airModel.AirMeasurementLocalization;
import sebaszczen.repository.AirDataRepository;
import sebaszczen.repository.SynopticDataRepository;
import sebaszczen.repository.AirMeasurementLocalizationRepository;
import sebaszczen.respository.MockAirConditionData;
import sebaszczen.respository.MockStationLocalization;
import sebaszczen.respository.MockSynopticStation;
import sebaszczen.services.api.ApiServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiServiceImplTest {

    @Mock
    private SynopticDataRepository synopticDataRepository;

    @Mock
    private AirDataRepository airDataRepository;

    @Mock
    private AirMeasurementLocalizationRepository airMeasurementLocalizationRepository;

    @Mock
    private ApiProvider apiProvider;

    @InjectMocks
    private ApiServiceImpl apiServiceImpl;
    private MockSynopticStation mockSynopticStation =new MockSynopticStation();
    private MockAirConditionData mockAirConditionData = new MockAirConditionData();
    private MockStationLocalization mockStationLocalization = new MockStationLocalization();

    @Test
    public void saveImgwData_JpaSaveMethodCalledTwoTimes() {
        List<SynopticData> synopticDataList = mockSynopticStation.getSynopticStationList();
        given(apiProvider.getAllSynopticStation()).willReturn(synopticDataList);
        when(synopticDataRepository.save(any(SynopticData.class))).thenReturn(synopticDataList.get(0));
        apiServiceImpl.saveImgwData();
        verify(synopticDataRepository,times(2)).save(any(SynopticData.class));
        verifyNoMoreInteractions(synopticDataRepository);
    }

    @Test
    public void saveData_getDataSaved() {
        List<SynopticData> synopticDataList = mockSynopticStation.getSynopticStationList();
        List<AirData> mockAirDataDtoList = mockAirConditionData.getAirConditionDataList();
        List<AirMeasurementLocalization> mockAirMeasurementLocalizationDtoList = mockStationLocalization.getStationLocalizationList();

        given(apiProvider.getAllSynopticStation()).willReturn(synopticDataList);
        given(apiProvider.getAllAirConditionData()).willReturn(mockAirDataDtoList);
//        given(apiProvider.getStationLocalization()).willReturn(mockAirMeasurementLocalizationDtoList);
        when(apiProvider.getSynopticDataByStationName(anyString())).thenReturn(Optional.of(synopticDataList.get(0)));
        when(apiProvider.getAirConditionDataByStationIndex(any(int.class))).thenReturn(Optional.of(mockAirDataDtoList.get(0)));

        when(synopticDataRepository.save(any(SynopticData.class))).thenReturn(synopticDataList.get(0));
        when(airMeasurementLocalizationRepository.save(any(AirMeasurementLocalization.class))).thenReturn(mockAirMeasurementLocalizationDtoList.get(0));
        when(airDataRepository.save(any(AirData.class))).thenReturn(mockAirDataDtoList.get(0));

        apiServiceImpl.saveData();

        verify(synopticDataRepository,times(2)).save(any(SynopticData.class));
        verify(airMeasurementLocalizationRepository,times(3)).save(any(AirMeasurementLocalization.class));
        verify(airDataRepository,times(2)).save(any(AirData.class));

    }
}
