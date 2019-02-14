package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sebaszczen.repository.CityRepository;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class DataController {

    @Autowired
    private CityRepository cityRepository;

    @RequestMapping(value = "/city", method = GET)
    public void getCity(@RequestParam(value = "name")String name) {
        cityRepository.findCityByName(name);
    }
}
