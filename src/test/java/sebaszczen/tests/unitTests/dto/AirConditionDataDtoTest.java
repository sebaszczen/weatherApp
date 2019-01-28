package sebaszczen.tests.unitTests.dto;

import org.junit.Test;
import sebaszczen.dto.AirConditionDataDto;
import sebaszczen.dto.StationLocalizationDto;
import sebaszczen.model.AirConditionData;
import sebaszczen.model.StationLocalization;
import sebaszczen.model.SynopticStation;
import sebaszczen.respository.MockAirConditionData;
import sebaszczen.respository.MockStationLocalization;
import sebaszczen.respository.MockSynopticStation;

import static org.junit.Assert.*;

public class AirConditionDataDtoTest {

    private MockAirConditionData mockAirConditionData = new MockAirConditionData();

    @Test
    public void convertToEntity_null() {
        AirConditionDataDto airConditionDataDto = new AirConditionDataDto();
        AirConditionData airConditionData = new AirConditionData(airConditionDataDto);
        assertEquals(airConditionData,airConditionDataDto.convertToEntity());
    }

    @Test
    public void convertToEntity() {
        AirConditionDataDto airConditionDataDto = mockAirConditionData.getMockAirConditionDataDtoList().get(0);
        AirConditionData airConditionData = new AirConditionData(airConditionDataDto);
        assertEquals(airConditionData,airConditionDataDto.convertToEntity());
    }


}