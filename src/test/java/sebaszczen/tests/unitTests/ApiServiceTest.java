package sebaszczen.tests.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import sebaszczen.apiProvider.ApiProvider;
import sebaszczen.domain.SynopticStation;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.respository.MockSynopticStationBuilder;
import sebaszczen.services.api.ApiServiceImpl;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiServiceTest {

    @Mock
    private ImgwApiRepository imgwApiRepository;

    @Mock
    private ApiProvider apiProvider;

    @InjectMocks
    private ApiServiceImpl apiService;
    private MockSynopticStationBuilder mockSynopticStationBuilder=new MockSynopticStationBuilder();


    @Test
    public void saveImgwData_JpaSaveMethodCalledTwoTimes() {
        List<SynopticStation.SynopticStationBuilder> synopticStationBuilderList = mockSynopticStationBuilder.getSynopticStationBuilderList();
        when(imgwApiRepository.save(any(SynopticStation.class))).thenReturn(new SynopticStation(synopticStationBuilderList.get(0)));
        given(apiProvider.getAllSynopticData()).willReturn(synopticStationBuilderList);
        apiService.saveImgwData();
        verify(imgwApiRepository,times(2)).save(any(SynopticStation.class));
    }
}
