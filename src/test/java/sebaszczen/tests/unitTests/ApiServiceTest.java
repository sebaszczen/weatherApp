package sebaszczen.tests.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.domain.SynopticStation;
import sebaszczen.domain.gios.AirConditionData;
import sebaszczen.domain.gios.StationLocalization;
import sebaszczen.domain.gios.dto.AirConditionDataDto;
import sebaszczen.domain.gios.dto.StationLocalizationDto;
import sebaszczen.repository.AirConditionDataRepository;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.repository.StationLocalizationRepository;
import sebaszczen.respository.MockAirConditionDataDto;
import sebaszczen.respository.MockStationLocalizationDto;
import sebaszczen.respository.MockSynopticStationDto;
import sebaszczen.services.api.ApiServiceImpl;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiServiceTest {

    @Mock
    private ImgwApiRepository imgwApiRepository;

    @Mock
    private AirConditionDataRepository airConditionDataRepository;

    @Mock
    private StationLocalizationRepository stationLocalizationRepository;

    @Mock
    private ApiProvider apiProvider;

    @InjectMocks
    private ApiServiceImpl apiService;
    private MockSynopticStationDto mockSynopticStationDto =new MockSynopticStationDto();
    private MockAirConditionDataDto mockAirConditionDataDto = new MockAirConditionDataDto();
    private MockStationLocalizationDto mockStationLocalizationDto = new MockStationLocalizationDto();

    @Test
    public void saveImgwData_JpaSaveMethodCalledTwoTimes() {
        List<SynopticStation.SynopticStationDto> synopticStationDtoList = mockSynopticStationDto.getSynopticStationDtoList();
        given(apiProvider.getAllSynopticStationDto()).willReturn(synopticStationDtoList);
        when(imgwApiRepository.save(any(SynopticStation.class))).thenReturn(new SynopticStation(synopticStationDtoList.get(0)));
        apiService.saveImgwData();
        verify(imgwApiRepository,times(2)).save(any(SynopticStation.class));
        verifyNoMoreInteractions(imgwApiRepository);
    }

    @Test
    public void saveData_getDataSaved() {
        List<SynopticStation.SynopticStationDto> synopticStationDtoList = mockSynopticStationDto.getSynopticStationDtoList();
        List<AirConditionDataDto> mockAirConditionDataDtoList = mockAirConditionDataDto.getMockAirConditionDataDtoList();
        List<StationLocalizationDto> mockStationLocalizationDtoList = mockStationLocalizationDto.getMockStationLocalizationDtoList();

        given(apiProvider.getAllSynopticStationDto()).willReturn(synopticStationDtoList);
        given(apiProvider.getAllAirConditionDataDto()).willReturn(mockAirConditionDataDtoList);
        given(apiProvider.getStationLocalizationDto()).willReturn(mockStationLocalizationDtoList);
        when(apiProvider.getSynopticDataByStationName(anyString())).thenReturn(synopticStationDtoList.get(0));
        when(apiProvider.getAirConditionDataByStationIndex(any(int.class))).thenReturn(mockAirConditionDataDtoList.get(0));

        when(imgwApiRepository.save(any(SynopticStation.class))).thenReturn(new SynopticStation(synopticStationDtoList.get(0)));
        when(stationLocalizationRepository.save(any(StationLocalization.class))).thenReturn(new StationLocalization(mockStationLocalizationDtoList.get(0)));
        when(airConditionDataRepository.save(any(AirConditionData.class))).thenReturn(new AirConditionData(mockAirConditionDataDtoList.get(0)));

        apiService.saveData();

        verify(imgwApiRepository,times(2)).save(any(SynopticStation.class));
        verify(stationLocalizationRepository,times(3)).save(any(StationLocalization.class));
        verify(airConditionDataRepository,times(2)).save(any(AirConditionData.class));

    }
}
