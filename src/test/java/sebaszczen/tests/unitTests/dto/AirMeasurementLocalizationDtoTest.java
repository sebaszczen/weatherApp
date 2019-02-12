package sebaszczen.tests.unitTests.dto;

import org.junit.Test;
import sebaszczen.dto.StationLocalizationDto;
import sebaszczen.model.airModel.AirMeasurementLocalization;
import sebaszczen.respository.MockStationLocalization;

import static org.junit.Assert.*;

public class AirMeasurementLocalizationDtoTest {

    private MockStationLocalization mockStationLocalization = new MockStationLocalization();

    @Test
    public void convertToEntity() {
        StationLocalizationDto stationLocalizationDto = mockStationLocalization.getMockStationLocalizationDtoList().get(0);
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