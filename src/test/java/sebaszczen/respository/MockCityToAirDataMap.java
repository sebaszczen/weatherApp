package sebaszczen.respository;

import sebaszczen.model.airModel.AirData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockCityToAirDataMap {

    public Map<String, List<AirData>> getCityToAirDataMock() {
        MockAirData mockAirData= new MockAirData();
        List<AirData> airDataList = mockAirData.getMockedAirData();
        Map<String, List<AirData>> cityToAirData = new HashMap<>();
        cityToAirData.put("warszawa", airDataList);
        cityToAirData.put("kraków", airDataList);
        cityToAirData.put("białystok", airDataList);
        cityToAirData.put("bydgoszcz", airDataList);
        return cityToAirData;
    }
}
