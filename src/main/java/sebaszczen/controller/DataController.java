package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sebaszczen.dto.AirDataDto;
import sebaszczen.dto.CityDto;
import sebaszczen.servicesFacade.WeatherServiceFacade;


import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/cities")
public class DataController {

    @Autowired
    private WeatherServiceFacade weatherServiceFacade;

//    @CrossOrigin(origins = "http://localhost:4200")
//    @RequestMapping(value = "/cities", method = GET)
//    public ResponseEntity<City> getDataForCity(@RequestParam(value = "name")String name, WebRequest webRequest) throws NotFoundException {
//        City lastDataForCityName = weatherServiceFacade.findLastDataForCityName(name);
//        return new ResponseEntity<City>(lastDataForCityName, HttpStatus.OK);
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{name}")
    public ResponseEntity<CityDto> getDataForCity(@PathVariable String name) {
        CityDto lastDataForCityName = weatherServiceFacade.findLastDataForCityName(name);
        Link selfLink = ControllerLinkBuilder.linkTo(methodOn(DataController.class).getDataForCity(name)).slash(name).withSelfRel();
        lastDataForCityName.add(selfLink);
        return new ResponseEntity<>(lastDataForCityName, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<Resources<CityDto>> getAllCities() {
        final List<CityDto> cityDtoList = weatherServiceFacade.findAllCities();
        for (CityDto cityDto : cityDtoList) {
            if (cityDto.getAirDataDtoList().size() > 0) {
                Link airDataLinks = linkTo(methodOn(DataController.class).getAirDataForCity(cityDto.getName())).withRel("airData");
                cityDto.add(airDataLinks);
            }
        }
        final Resources<CityDto> resources = new Resources<>(cityDtoList);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{name}/airData")
    public ResponseEntity<List<AirDataDto>> getAirDataForCity(@PathVariable String name) {
        List<AirDataDto> airDataList = weatherServiceFacade.findAirDataForCity(name);
        return new ResponseEntity<>(airDataList,HttpStatus.OK);
    }
}
