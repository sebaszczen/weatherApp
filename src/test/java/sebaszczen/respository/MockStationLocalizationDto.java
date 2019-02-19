package sebaszczen.respository;

import sebaszczen.dto.AirCityDto;
import sebaszczen.dto.CommuneDto;
import sebaszczen.dto.StationLocalizationDto;
import sebaszczen.model.airModel.AirMeasurementLocalization;

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
        AirCityDto airCityDto = new AirCityDto(1,"name",communeDto);
        StationLocalizationDto stationLocalizationDto = new StationLocalizationDto();
        stationLocalizationDto.setAddressStreet("Warszawa");
        stationLocalizationDto.setAirCityDto(airCityDto);
        stationLocalizationDto.setGegrLat("gegrLat");
        stationLocalizationDto.setGegrLon("gegrLon");
        stationLocalizationDto.setStationId(1);
        stationLocalizationDto.setStationName("name");

        CommuneDto communeDto2 = new CommuneDto();
        communeDto2.setCommuneName("mazowsze");
        communeDto2.setDistrictName("podlasie");
        communeDto2.setProvinceName("province");
        AirCityDto airCityDto2 = new AirCityDto(2,"name",communeDto2);
        StationLocalizationDto stationLocalizationDto2 = new StationLocalizationDto();
        stationLocalizationDto2.setAddressStreet("Warszawa");
        stationLocalizationDto2.setAirCityDto(airCityDto);
        stationLocalizationDto2.setGegrLat("gegrLat");
        stationLocalizationDto2.setGegrLon("gegrLon");
        stationLocalizationDto2.setStationId(1);
        stationLocalizationDto2.setStationName("name");

        CommuneDto communeDto3 = new CommuneDto();
        communeDto3.setCommuneName("mazowsze");
        communeDto3.setDistrictName("podlasie");
        communeDto3.setProvinceName("province");
        AirCityDto airCityDto3 = new AirCityDto(3,"name",communeDto3);
        StationLocalizationDto stationLocalizationDto3 = new StationLocalizationDto();
        stationLocalizationDto3.setAddressStreet("Warszawa");
        stationLocalizationDto3.setAirCityDto(airCityDto);
        stationLocalizationDto3.setGegrLat("gegrLat");
        stationLocalizationDto3.setGegrLon("gegrLon");
        stationLocalizationDto3.setStationId(1);
        stationLocalizationDto3.setStationName("name");

        list.add(stationLocalizationDto);
        list.add(stationLocalizationDto2);
        list.add(stationLocalizationDto3);

        return list;
    }

    public List<AirMeasurementLocalization> getStationLocalizationList() {
        return getMockStationLocalizationDtoList().parallelStream().map(AirMeasurementLocalization::new).collect(Collectors.toList());
    }
}
