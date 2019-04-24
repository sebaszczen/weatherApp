package sebaszczen.respository;

import sebaszczen.model.synopticModel.SynopticData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockCityToSynopticDataMap {

    public Map<String, List<SynopticData>> getCityToSynopticDataMock(){
        MockSynopticData mockSynopticData = new MockSynopticData();
        List<SynopticData> mockedSynopticData = mockSynopticData.getSynopticData();
        Map<String, List<SynopticData>> cityToAirData = new HashMap<>();
        cityToAirData.put("warszawa", mockedSynopticData);
        cityToAirData.put("kraków", mockedSynopticData);
        cityToAirData.put("bydgoszcz", mockedSynopticData);
        cityToAirData.put("białystok", mockedSynopticData);
        return cityToAirData;
    }
}
