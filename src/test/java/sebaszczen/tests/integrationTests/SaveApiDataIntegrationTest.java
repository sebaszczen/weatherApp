package sebaszczen.tests.integrationTests;

import com.sun.prism.impl.Disposer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sebaszczen.services.api.ApiService;
import sebaszczen.services.api.ApiServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveApiDataIntegrationTest {

    @Autowired
    private ApiServiceImpl apiService;

    @Test
    public void saveData_getDataSaved() {
        apiService.saveData();
    }
}
