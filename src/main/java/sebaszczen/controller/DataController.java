package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value = "/city", method = GET)
    public City getCity(@RequestParam(value = "name")String name) {
        return dataService.findLastDataForCityName(name);
    }
}
