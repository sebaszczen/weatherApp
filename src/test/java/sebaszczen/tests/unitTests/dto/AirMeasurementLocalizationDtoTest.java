package sebaszczen.tests.unitTests.dto;

import org.junit.Test;
import sebaszczen.dto.StationLocalizationDto;
import sebaszczen.model.airModel.AirMeasurementLocalization;
import sebaszczen.respository.MockStationLocalizationDto;

import static org.junit.Assert.assertEquals;

public class AirMeasurementLocalizationDtoTest {

    private MockStationLocalizationDto mockStationLocalizationDto = new MockStationLocalizationDto();

    @Test
    public void convertToEntity() {
        StationLocalizationDto stationLocalizationDto = mockStationLocalizationDto.getMockStationLocalizationDtoList().get(0);
        AirMeasurementLocalization airMeasurementLocalization = new AirMeasurementLocalization(stationLocalizationDto);
        assertEquals(airMeasurementLocalization,stationLocalizationDto.convertToEntity());
    }

    @Test
    public void convertToEntity_Null() {
        StationLocalizationDto stationLocalizationDto = new StationLocalizationDto();
        AirMeasurementLocalization airMeasurementLocalization = new AirMeasurementLocalization(stationLocalizationDto);
        assertEquals(airMeasurementLocalization,stationLocalizationDto.convertToEntity());
    }
}