package sebaszczen.tests.unitTests.dto;

import org.junit.Test;
import sebaszczen.dto.StationLocalizationDto;
import sebaszczen.model.StationLocalization;
import sebaszczen.respository.MockStationLocalization;

import static org.junit.Assert.*;

public class StationLocalizationDtoTest {

    private MockStationLocalization mockStationLocalization = new MockStationLocalization();

    @Test
    public void convertToEntity() {
        StationLocalizationDto stationLocalizationDto = mockStationLocalization.getMockStationLocalizationDtoList().get(0);
        StationLocalization stationLocalization = new StationLocalization(stationLocalizationDto);
        assertEquals(stationLocalization,stationLocalizationDto.convertToEntity());
    }

    @Test
    public void convertToEntity_Null() {
        StationLocalizationDto stationLocalizationDto = new StationLocalizationDto();
        StationLocalization stationLocalization = new StationLocalization(stationLocalizationDto);
        assertEquals(stationLocalization,stationLocalizationDto.convertToEntity());
    }
}