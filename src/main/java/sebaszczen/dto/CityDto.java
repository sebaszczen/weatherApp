package sebaszczen.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.ResourceSupport;
import sebaszczen.controller.DataController;
import sebaszczen.model.cityModel.City;
import sebaszczen.model.synopticModel.SynopticData;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CityDto extends ResourceSupport {

    private String name;
    private List<SynopticData.SynoptiDataDto> synopticDataDtoList = new ArrayList<>();
    private List<AirDataDto> airDataDtoList= new ArrayList<>();

    public CityDto(City city) {
        this.name = city.getName();
        city.getAirDataList().forEach(airData->{
            AirDataDto airDataDto = new AirDataDto(airData);
            airDataDtoList.add(airDataDto);
        });
        city.getSynopticDataList().forEach(synopticData -> {
            SynopticData.SynoptiDataDto synoptiDataDto = new SynopticData.SynoptiDataDto();
            BeanUtils.copyProperties(synopticData,synoptiDataDto);
            synopticDataDtoList.add(synoptiDataDto);
        });
        add(linkTo(DataController.class).withRel("cities"));
        add(linkTo(methodOn(DataController.class).getDataForCity(name,null)).withSelfRel());
    }

    public String getName() {
        return name;
    }

    public List<SynopticData.SynoptiDataDto> getSynopticDataDtoList() {
        return synopticDataDtoList;
    }

    public List<AirDataDto> getAirDataDtoList() {
        return airDataDtoList;
    }
}
