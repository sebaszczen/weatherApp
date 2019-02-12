package sebaszczen.tests.unitTests.dto;

import org.junit.Test;
import sebaszczen.dto.AirConditionDataDto;
import sebaszczen.model.airModel.AirData;
import sebaszczen.respository.MockAirConditionData;

import static org.junit.Assert.*;

public class AirDataDtoTest {

    private MockAirConditionData mockAirConditionData = new MockAirConditionData();

    @Test
    public void convertToEntity_null() {
        AirConditionDataDto airConditionDataDto = new AirConditionDataDto();
        AirData airData = new AirData(airConditionDataDto);
        assertEquals(airData,airConditionDataDto.convertToEntity());
    }

    @Test
    public void convertToEntity() {
        AirConditionDataDto airConditionDataDto = mockAirConditionData.getMockAirConditionDataDtoList().get(0);
        AirData airData = new AirData(airConditionDataDto);
        assertEquals(airData,airConditionDataDto.convertToEntity());
    }


}