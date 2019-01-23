package sebaszczen.respository;

import sebaszczen.dto.CityDto;
import sebaszczen.dto.CommuneDto;
import sebaszczen.dto.StationLocalizationDto;
import sebaszczen.model.StationLocalization;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockStationLocalizationDto {

    public List<StationLocalizationDto> getMockStationLocalizationDtoList() {
        List <StationLocalizationDto> list = new ArrayList();
        CommuneDto communeDto = new CommuneDto();
        communeDto.setCommuneName("mazowsze");
        communeDto.setDistrictName("podlasie");
        communeDto.setProvinceName("province");
        CityDto cityDto = new CityDto();
        cityDto.setCityId(1);
        cityDto.setCommuneDto(communeDto);
        cityDto.setName("name");
        StationLocalizationDto stationLocalizationDto = new StationLocalizationDto();
        stationLocalizationDto.setAddressStreet("Warszawa");
        stationLocalizationDto.setCityDto(cityDto);
        stationLocalizationDto.setGegrLat("gegrLat");
        stationLocalizationDto.setGegrLon("gegrLon");
        stationLocalizationDto.setStationId(1);
        stationLocalizationDto.setStationName("name");

        CommuneDto communeDto2 = new CommuneDto();
        communeDto2.setCommuneName("mazowsze");
        communeDto2.setDistrictName("podlasie");
        communeDto2.setProvinceName("province");
        CityDto cityDto2 = new CityDto();
        cityDto2.setCityId(1);
        cityDto2.setCommuneDto(communeDto);
        cityDto2.setName("name");
        StationLocalizationDto stationLocalizationDto2 = new StationLocalizationDto();
        stationLocalizationDto2.setAddressStreet("Warszawa");
        stationLocalizationDto2.setCityDto(cityDto);
        stationLocalizationDto2.setGegrLat("gegrLat");
        stationLocalizationDto2.setGegrLon("gegrLon");
        stationLocalizationDto2.setStationId(1);
        stationLocalizationDto2.setStationName("name");

        CommuneDto communeDto3 = new CommuneDto();
        communeDto3.setCommuneName("mazowsze");
        communeDto3.setDistrictName("podlasie");
        communeDto3.setProvinceName("province");
        CityDto cityDto3 = new CityDto();
        cityDto3.setCityId(1);
        cityDto3.setCommuneDto(communeDto);
        cityDto3.setName("name");
        StationLocalizationDto stationLocalizationDto3 = new StationLocalizationDto();
        stationLocalizationDto3.setAddressStreet("Warszawa");
        stationLocalizationDto3.setCityDto(cityDto);
        stationLocalizationDto3.setGegrLat("gegrLat");
        stationLocalizationDto3.setGegrLon("gegrLon");
        stationLocalizationDto3.setStationId(1);
        stationLocalizationDto3.setStationName("name");

        list.add(stationLocalizationDto);
        list.add(stationLocalizationDto2);
        list.add(stationLocalizationDto3);

        return list;
    }

    public List<StationLocalization> getStationLocalizationList() {
        return getMockStationLocalizationDtoList().parallelStream().map(StationLocalization::new).collect(Collectors.toList());
    }
}
