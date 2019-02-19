package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sebaszczen.model.SynopticData;
import sebaszczen.repository.CityRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class DataController {

    @Autowired
    private CityRepository cityRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/city", method = GET)
    public List<SynopticData> getCity(@RequestParam(value = "name")String name) {
        return cityRepository.findCityByName(name).getSynopticDataList();
    }
}
