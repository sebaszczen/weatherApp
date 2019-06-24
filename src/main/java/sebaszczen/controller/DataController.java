package sebaszczen.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sebaszczen.dto.AirDataDto;
import sebaszczen.dto.CityDto;
import sebaszczen.services.externalApi.ApiServiceImpl;
import sebaszczen.servicesFacade.WeatherServiceFacade;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/cities")
public class
DataController {

    @Autowired
    private WeatherServiceFacade weatherServiceFacade;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("dynamicFiltering")
    public MappingJacksonValue dynamicFiltering() {
        CityDto warszawa = weatherServiceFacade.findLastDataForCityName("warszawa");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(warszawa);
        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("name");
        FilterProvider filters= new SimpleFilterProvider().addFilter("mojFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/hello")
    public String hello () {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

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
