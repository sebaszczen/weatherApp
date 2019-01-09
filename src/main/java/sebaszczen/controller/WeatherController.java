package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sebaszczen.services.api.SynopticDataServiceImpl;

@RestController
public class WeatherController {

    @Autowired
    private SynopticDataServiceImpl apiSupplier;

//    @PostMapping("/save")
//    public void saveSynopticData() {
//        apiSupplier.saveWeatherMeasurements();
//    }
//
//    @PostMapping("/save")
//    public void saveAirConditionData() {
//        apiSupplier.saveAirConditions();
//    }
}
