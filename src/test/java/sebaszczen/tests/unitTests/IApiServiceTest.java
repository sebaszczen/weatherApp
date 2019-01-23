package sebaszczen.tests.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.model.SynopticStation;
import sebaszczen.model.AirConditionData;
import sebaszczen.model.StationLocalization;
import sebaszczen.repository.AirConditionDataRepository;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.repository.StationLocalizationRepository;
import sebaszczen.respository.MockAirConditionDataDto;
import sebaszczen.respository.MockStationLocalizationDto;
import sebaszczen.respository.MockSynopticStationDto;
import sebaszczen.services.api.ApiService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IApiServiceTest {

    @Mock
    private ImgwApiRepository imgwApiRepository;

    @Mock
    private AirConditionDataRepository airConditionDataRepository;

    @Mock
    private StationLocalizationRepository stationLocalizationRepository;

    @Mock
    private ApiProvider apiProvider;

    @InjectMocks
    private ApiService apiService;
    private MockSynopticStationDto mockSynopticStationDto =new MockSynopticStationDto();
    private MockAirConditionDataDto mockAirConditionDataDto = new MockAirConditionDataDto();
    private MockStationLocalizationDto mockStationLocalizationDto = new MockStationLocalizationDto();

    @Test
    public void saveImgwData_JpaSaveMethodCalledTwoTimes() {
        List<SynopticStation> synopticStationList = mockSynopticStationDto.getSynopticStationList();
        given(apiProvider.getAllSynopticStation()).willReturn(synopticStationList);
        when(imgwApiRepository.save(any(SynopticStation.class))).thenReturn(synopticStationList.get(0));
        apiService.saveImgwData();
        verify(imgwApiRepository,times(2)).save(any(SynopticStation.class));
        verifyNoMoreInteractions(imgwApiRepository);
    }

    @Test
    public void saveData_getDataSaved() {
        List<SynopticStation> synopticStationList = mockSynopticStationDto.getSynopticStationList();
        List<AirConditionData> mockAirConditionDataDtoList = mockAirConditionDataDto.getAirConditionDataList();
        List<StationLocalization> mockStationLocalizationDtoList = mockStationLocalizationDto.getStationLocalizationList();

        given(apiProvider.getAllSynopticStation()).willReturn(synopticStationList);
        given(apiProvider.getAllAirConditionData()).willReturn(mockAirConditionDataDtoList);
        given(apiProvider.getStationLocalization()).willReturn(mockStationLocalizationDtoList);
        when(apiProvider.getSynopticDataByStationName(anyString())).thenReturn(synopticStationList.get(0));
        when(apiProvider.getAirConditionDataByStationIndex(any(int.class))).thenReturn(mockAirConditionDataDtoList.get(0));

        when(imgwApiRepository.save(any(SynopticStation.class))).thenReturn(synopticStationList.get(0));
        when(stationLocalizationRepository.save(any(StationLocalization.class))).thenReturn(mockStationLocalizationDtoList.get(0));
        when(airConditionDataRepository.save(any(AirConditionData.class))).thenReturn(mockAirConditionDataDtoList.get(0));

        apiService.saveData();

        verify(imgwApiRepository,times(2)).save(any(SynopticStation.class));
        verify(stationLocalizationRepository,times(3)).save(any(StationLocalization.class));
        verify(airConditionDataRepository,times(2)).save(any(AirConditionData.class));

    }
}
