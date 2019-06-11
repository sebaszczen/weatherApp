package sebaszczen.tests.unitTests.dto;

import org.junit.Test;
import sebaszczen.dto.AirDataDto;
import sebaszczen.model.airModel.AirData;
import sebaszczen.respository.MockAirData;

import static org.junit.Assert.assertEquals;

public class AirDataDtoTest {

    private MockAirData mockAirData = new MockAirData();

    @Test
    public void convertToEntity_null() {
        AirDataDto airDataDto = new AirDataDto();
        AirData airData = new AirData(airDataDto);
        assertEquals(airData, airDataDto.convertToEntity());
    }

    @Test
    public void convertToEntity() {
        AirDataDto airDataDto = mockAirData.getMockedAirDataDto().get(0);
        AirData airData = new AirData(airDataDto);
        assertEquals(airData, airDataDto.convertToEntity());
    }


}