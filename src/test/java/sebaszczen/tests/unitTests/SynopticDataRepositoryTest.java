package sebaszczen.tests.unitTests;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import sebaszczen.model.SynopticData;
import sebaszczen.repository.SynopticDataRepository;
import sebaszczen.respository.MockSynopticData;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE )
public class SynopticDataRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private SynopticDataRepository synopticDataRepository;

    private MockSynopticData mockSynopticData =new MockSynopticData();

    @Test
    public void saveImgwData() {
        List<SynopticData.SynopticStationDto> synopticStationDtoList = mockSynopticData.getSynopticStationDto();
        List<SynopticData> synopticDataList = synopticStationDtoList.parallelStream().map(SynopticData::new).collect(Collectors.toList());
        synopticDataList.forEach(testEntityManager::persist);
        List<SynopticData> all = synopticDataRepository.findAll();
        Assertions.assertThat(all).isEqualTo(synopticDataList);
    }

}
