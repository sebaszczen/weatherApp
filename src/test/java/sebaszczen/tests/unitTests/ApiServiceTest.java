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

    @Mock
    private ApiServiceImpl apiService;
    private MockSynopticStationBuilder mockSynopticStationBuilder=new MockSynopticStationBuilder();


    @Test
    public void saveImgwData_provideSynopticStationList() {
        List<SynopticStation.SynopticStationBuilder> synopticStationBuilderList = mockSynopticStationBuilder.getSynopticStationBuilderList();

//        doNothing().when(imgwApiRepository).save(any());
        given(apiProvider.getAllSynopticData()).willReturn(synopticStationBuilderList);
        apiService.saveImgwData();
        verify(apiService,times(1)).saveImgwData();
    }
}
