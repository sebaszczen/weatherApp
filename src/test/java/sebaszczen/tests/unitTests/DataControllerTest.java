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
import sebaszczen.controller.DataController;
import sebaszczen.model.City;
import sebaszczen.services.DataService;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(DataController.class)
@RunWith(SpringRunner.class)
public class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataService dataService;

    @Test
    public void saveCurrentDataOnDemand() throws Exception {
        when(dataService.findLastDataForCityName(anyString())).thenReturn(new City());
        mockMvc.perform(MockMvcRequestBuilders.get("/cities?name=warszawa"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
