package sebaszczen.tests.unitTests.dto;

import org.junit.Test;
import sebaszczen.model.SynopticData;
import sebaszczen.respository.MockSynopticStation;

import static org.junit.Assert.*;

public class SynopticDataTest {

    private MockSynopticStation mockSynopticStation = new MockSynopticStation();


    @Test
    public void synopticStation_convertToEntity() {
        SynopticData.SynopticStationDto synopticStationDto = mockSynopticStation.getSynopticStationDtoList().get(0);
        SynopticData synopticData = new SynopticData(synopticStationDto);
        assertEquals(synopticData,synopticStationDto.convertToEntity());
    }

}