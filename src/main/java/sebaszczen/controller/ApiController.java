package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sebaszczen.services.api.ApiService;

import java.net.URI;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ApiController {

    private final ApiService synopticDataService;

    @Autowired
    public ApiController(ApiService synopticDataService) {
        this.synopticDataService = synopticDataService;
    }

    @RequestMapping(value = "/api",method = POST)
    public ResponseEntity saveCurrentDataOnDemand
            (@RequestParam(value = "provider")String type) {
        if (type.equals("imgw")) {
            synopticDataService.saveImgwData();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }
//
//    @PostMapping("/save")
//    public void saveAirConditionData() {
//        apiProvider.saveAirConditions();
//    }
}
