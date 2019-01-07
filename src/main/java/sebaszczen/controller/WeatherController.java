package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sebaszczen.services.api.ApiSupplier;
import sebaszczen.services.api.ApiSupplierImpl;

@Controller
public class WeatherController {

    @Autowired
    private ApiSupplierImpl apiSupplier;

    @GetMapping("/save")
    public void saveMeasurements() {
        apiSupplier.saveWeatherMeasurements();
    }
}
