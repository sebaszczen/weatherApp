package sebaszczen.tests.unitTests.dto;

import org.junit.Test;
import sebaszczen.model.SynopticStation;
import sebaszczen.respository.MockSynopticStation;

import static org.junit.Assert.*;

public class SynopticStationTest {

    private MockSynopticStation mockSynopticStation = new MockSynopticStation();


    @Test
    public void synopticStation_convertToEntity() {
        SynopticStation.SynopticStationDto synopticStationDto = mockSynopticStation.getSynopticStationDtoList().get(0);
        SynopticStation synopticStation = new SynopticStation(synopticStationDto);
        assertEquals(synopticStation,synopticStationDto.convertToEntity());
    }

    @Test
    public void synopticStation_convertToEntityNull() {
        SynopticStation.SynopticStationDto synopticStationDto = new SynopticStation.SynopticStationDto();
        SynopticStation convertToEntity = synopticStationDto.convertToEntity();
        SynopticStation synopticStation = new SynopticStation(synopticStationDto);
        assertEquals(synopticStation,convertToEntity);
    }
}