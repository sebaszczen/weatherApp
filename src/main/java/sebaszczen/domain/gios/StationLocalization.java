package sebaszczen.domain.gios;

import sebaszczen.domain.gios.dto.CityDto;
import sebaszczen.domain.gios.dto.StationLocalizationDto;

import javax.persistence.*;

@Entity
public class StationLocalization {
    @Id
    @GeneratedValue
    private Long id;
    private int stationId;
    private String stationName;
    private String gegrLat;
    private String gegrLon;
    @Embedded
    private City city;
    private String addressStreet;

    public StationLocalization(StationLocalizationDto stationLocalizationDto) {
        this.stationId = stationLocalizationDto.getStationId();
        this.stationName = stationLocalizationDto.getStationName();
        this.gegrLat = stationLocalizationDto.getGegrLat();
        this.gegrLon = stationLocalizationDto.getGegrLon();
        CityDto cityDto = stationLocalizationDto.getCityDto();
        this.city = new City(cityDto.getCityId(),cityDto.getName(),cityDto.getCommuneDto()) ;
        this.addressStreet = stationLocalizationDto.getAddressStreet();
    }
}
