package sebaszczen.tests.unitTests.dto;

import org.junit.Test;
import sebaszczen.model.SynopticData;
import sebaszczen.respository.MockSynopticData;

import static org.junit.Assert.*;

public class SynopticDataTest {

    private MockSynopticData mockSynopticData = new MockSynopticData();


    @Test
    public void synopticStation_convertToEntity() {
        SynopticData.SynoptiDataDto synoptiDataDto = mockSynopticData.getSynopticStationDto().get(0);
        SynopticData synopticData = new SynopticData(synoptiDataDto);
        assertEquals(synopticData, synoptiDataDto.convertToEntity());
    }

}