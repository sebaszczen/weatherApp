package sebaszczen.respository;

import sebaszczen.model.cityModel.City;
import sebaszczen.model.synopticModel.SynopticData;
import sebaszczen.model.airModel.AirData;

import java.util.ArrayList;
import java.util.List;

public class MockCity {

    public List<City> getMockedCity() {
        List<City> list = new ArrayList();
        MockAirData mockAirData = new MockAirData();
        List<AirData> airDataList = mockAirData.getMockedAirData();
        MockSynopticData mockSynopticData = new MockSynopticData();
        List<SynopticData> synopticDataList = mockSynopticData.getSynopticData();
        City city1 = new City("warszawa",synopticDataList,airDataList);
        City city2 = new City("kraków",synopticDataList,airDataList);
        City city3 = new City("białystok",synopticDataList,airDataList);
        City city4 = new City("bydgoszcz",synopticDataList,airDataList);
        list.add(city1);
        list.add(city2);
        list.add(city3);
        list.add(city4);

        return list;
    }

}
