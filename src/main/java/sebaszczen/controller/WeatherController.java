package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sebaszczen.services.api.SynopticDataServiceImpl;

@RestController
public class WeatherController {

    @Autowired
    private SynopticDataServiceImpl synopticDataService;

    @PostMapping("/save")
    public void saveSynopticData() {
        synopticDataService.saveSynopticData();
    }
//
//    @PostMapping("/save")
//    public void saveAirConditionData() {
//        apiSupplier.saveAirConditions();
//    }
}
