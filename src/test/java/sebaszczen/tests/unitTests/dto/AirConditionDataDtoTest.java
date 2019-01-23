package sebaszczen.tests.unitTests.dto;

import org.junit.Assert;
import org.junit.Test;
import sebaszczen.dto.AirConditionDataDto;
import sebaszczen.dto.StationLocalizationDto;
import sebaszczen.model.AirConditionData;
import sebaszczen.model.StationLocalization;
import sebaszczen.model.SynopticStation;
import sebaszczen.respository.MockAirConditionDataDto;
import sebaszczen.respository.MockStationLocalizationDto;
import sebaszczen.respository.MockSynopticStationDto;

import static org.junit.Assert.*;

public class AirConditionDataDtoTest {

    private MockAirConditionDataDto mockAirConditionDataDto = new MockAirConditionDataDto();
    private MockSynopticStationDto mockSynopticStationDto = new MockSynopticStationDto();
    private MockStationLocalizationDto mockStationLocalizationDto = new MockStationLocalizationDto();

    @Test
    public void convertToEntity() {
        SynopticStation.SynopticStationDto synopticStationDto = mockSynopticStationDto.getSynopticStationDtoList().get(0);
        SynopticStation synopticStation = new SynopticStation(synopticStationDto);
        assertEquals(synopticStation,synopticStationDto.convertToEntity());
    }

    @Test
    public void synopticStation_ConvertToEntity() {
        AirConditionDataDto airConditionDataDto = mockAirConditionDataDto.getMockAirConditionDataDtoList().get(0);
        AirConditionData airConditionData = new AirConditionData(airConditionDataDto);
        assertEquals(airConditionData,airConditionDataDto.convertToEntity());
    }

    @Test
    public void stationLocalization_ConvertToEntity() {
        StationLocalizationDto stationLocalizationDto = mockStationLocalizationDto.getMockStationLocalizationDtoList().get(0);
        StationLocalization stationLocalization = new StationLocalization(stationLocalizationDto);
        assertEquals(stationLocalization,stationLocalizationDto.convertToEntity());
    }
}