package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sebaszczen.services.api.SynopticDataService;
import sebaszczen.services.api.SynopticDataServiceImpl;

import java.net.URI;

@RestController
public class WeatherController {

    @Autowired
    private SynopticDataService synopticDataService;

    @PostMapping("/save")
    public ResponseEntity saveSynopticData() {
        synopticDataService.saveSynopticData();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }
//
//    @PostMapping("/save")
//    public void saveAirConditionData() {
//        apiSupplier.saveAirConditions();
//    }
}
