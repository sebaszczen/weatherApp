package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sebaszczen.dto.AirDataDto;
import sebaszczen.dto.CityDto;
import sebaszczen.services.externalApi.ApiServiceImpl;
import sebaszczen.servicesFacade.WeatherServiceFacade;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/cities")
public class
DataController {

    @Autowired
    private WeatherServiceFacade weatherServiceFacade;

    @GetMapping("/{name}")
    public ResponseEntity<CityDto> getDataForCity(@PathVariable String name, WebRequest webRequest) {
        if (webRequest.checkNotModified(ApiServiceImpl.localDateTime.toString())) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .cacheControl(CacheControl.maxAge(0, TimeUnit.SECONDS).cachePrivate().mustRevalidate())
                    .lastModified(ApiServiceImpl.localDateTime.toEpochSecond(ZoneOffset.UTC))
                    .build();
        }
        CityDto lastDataForCityName = weatherServiceFacade.findLastDataForCityName(name);
        return ResponseEntity.ok().lastModified(ApiServiceImpl.localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()).cacheControl(CacheControl.maxAge(0, TimeUnit.SECONDS).cachePublic()).body(lastDataForCityName);
    }

    @GetMapping
    public ResponseEntity<Resources<CityDto>> getAllCities() {
        final List<CityDto> cityDtoList = weatherServiceFacade.findAllCities();
        for (CityDto cityDto : cityDtoList) {
            if (cityDto.getAirDataDtoList().size() > 0) {
                Link airDataLinks = linkTo(methodOn(DataController.class).getAllAirDataForCity(cityDto.getName())).withRel("airData");
                cityDto.add(airDataLinks);
            }
        }
        final Resources<CityDto> resources = new Resources<>(cityDtoList);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(22, TimeUnit.MINUTES).cachePublic()).body(resources);
    }

    @GetMapping("/{name}/airData")
    public ResponseEntity<List<AirDataDto>> getAllAirDataForCity(@PathVariable String name) {
        List<AirDataDto> airDataList = weatherServiceFacade.findAllAirDataForCity(name);
        return new ResponseEntity<>(airDataList,HttpStatus.OK);
    }
}
