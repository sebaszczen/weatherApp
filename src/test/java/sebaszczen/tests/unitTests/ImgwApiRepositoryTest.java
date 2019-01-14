package sebaszczen.tests.unitTests;

import org.assertj.core.api.Assertions;
import org.hibernate.mapping.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import sebaszczen.domain.SynopticStation;
import sebaszczen.repository.ImgwApiRepository;
import sebaszczen.respository.MockSynopticStationBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE )
public class ImgwApiRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ImgwApiRepository imgwApiRepository;

    private MockSynopticStationBuilder mockSynopticStationBuilder=new MockSynopticStationBuilder();

    @Test
    public void saveImgwData() {
        List<SynopticStation.SynopticStationBuilder> synopticStationBuilderList = mockSynopticStationBuilder.getSynopticStationBuilderList();
        List<SynopticStation> synopticStationList = synopticStationBuilderList.parallelStream().map(SynopticStation::new).collect(Collectors.toList());
        synopticStationList.forEach(testEntityManager::persist);
        List<SynopticStation> all = imgwApiRepository.findAll();
        Assertions.assertThat(all).isEqualTo(synopticStationList);
    }
}
