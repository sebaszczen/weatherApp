package sebaszczen.respository;

import sebaszczen.dto.AirDataDto;
import sebaszczen.dto.AirQualityDto;
import sebaszczen.model.airModel.AirData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockAirData {

    public List<AirDataDto> getMockedAirDataDto() {
        List <AirDataDto> list = new ArrayList();

        AirQualityDto airQualityDto1 = new AirQualityDto();
        airQualityDto1.setAirConditionInWord("super");
        airQualityDto1.setAirLevelByNumber(1);

        AirDataDto airDataDto1 = new AirDataDto();
        airDataDto1.setC6h6IndexLevel(airQualityDto1);
        airDataDto1.setCoIndexLevel(airQualityDto1);
        airDataDto1.setNo2IndexLevel(airQualityDto1);
        airDataDto1.setStCalcDate("2019-01-12 12:20");


        AirDataDto airDataDto2 = new AirDataDto();
        airDataDto2.setC6h6IndexLevel(airQualityDto1);
        airDataDto2.setCoIndexLevel(airQualityDto1);
        airDataDto2.setNo2IndexLevel(airQualityDto1);
        airDataDto2.setStCalcDate("2019-01-12 12:20");


        list.add(airDataDto1);
        list.add(airDataDto2);

        return list;
    }

    public List<AirData> getMockedAirData() {
        return getMockedAirDataDto().parallelStream().map(AirData::new).collect(Collectors.toList());
    }
}
