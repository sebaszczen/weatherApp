package sebaszczen.respository;

import sebaszczen.domain.gios.dto.AirConditionDataDto;
import sebaszczen.domain.gios.dto.LevelDto;

import java.util.ArrayList;
import java.util.List;

public class MockAirConditionDataDto {

    public List<AirConditionDataDto> getMockAirConditionDataDtoList() {
        List <AirConditionDataDto> list = new ArrayList();

        LevelDto levelDto1 = new LevelDto();
        levelDto1.setAirConditionInWord("super");
        levelDto1.setAirLevelByNumber(1);
        AirConditionDataDto airConditionDataDto1 = new AirConditionDataDto();
        airConditionDataDto1.setC6h6IndexLevel(levelDto1);
        airConditionDataDto1.setCoIndexLevel(levelDto1);
        airConditionDataDto1.setNo2IndexLevel(levelDto1);


        LevelDto levelDto2 = new LevelDto();
        levelDto1.setAirConditionInWord("super");
        levelDto1.setAirLevelByNumber(1);
        AirConditionDataDto airConditionDataDto2 = new AirConditionDataDto();
        airConditionDataDto1.setC6h6IndexLevel(levelDto2);
        airConditionDataDto1.setCoIndexLevel(levelDto2);
        airConditionDataDto1.setNo2IndexLevel(levelDto2);

        list.add(airConditionDataDto1);
        list.add(airConditionDataDto2);

        return list;
    }
}
