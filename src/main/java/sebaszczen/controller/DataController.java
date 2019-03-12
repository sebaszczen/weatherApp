package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import sebaszczen.model.City;
import sebaszczen.model.SynopticData;
import sebaszczen.repository.AirDataRepository;
import sebaszczen.repository.CityRepository;
import sebaszczen.repository.SynopticDataRepository;
import sebaszczen.services.DataService;

import javax.persistence.EntityManagerFactory;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/cities", method = GET)
    public ResponseEntity<City> getCity(@RequestParam(value = "name")String name, WebRequest webRequest) {
        City lastDataForCityName = dataService.findLastDataForCityName(name);
        return new ResponseEntity<City>(lastDataForCityName, HttpStatus.OK);
    }
}
