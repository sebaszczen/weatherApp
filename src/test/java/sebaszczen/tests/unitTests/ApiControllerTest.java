package sebaszczen.tests.unitTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sebaszczen.services.api.ApiService;

import static org.mockito.Mockito.doNothing;

@WebMvcTest
@RunWith(SpringRunner.class)
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiService ApiService;

    @Test
    public void saveCurrentDataOnDemand() throws Exception {
        doNothing().when(ApiService).saveImgwData();
        mockMvc.perform(MockMvcRequestBuilders.post("/api?provider=imgw"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
