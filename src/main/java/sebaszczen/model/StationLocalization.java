package sebaszczen.model;

import sebaszczen.dto.CityDto;
import sebaszczen.dto.StationLocalizationDto;

import javax.persistence.*;
import java.util.Objects;

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

    public int getStationId() {
        return stationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationLocalization that = (StationLocalization) o;
        return stationId == that.stationId &&
                Objects.equals(id, that.id) &&
                Objects.equals(stationName, that.stationName) &&
                Objects.equals(gegrLat, that.gegrLat) &&
                Objects.equals(gegrLon, that.gegrLon) &&
                Objects.equals(city, that.city) &&
                Objects.equals(addressStreet, that.addressStreet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stationId, stationName, gegrLat, gegrLon, city, addressStreet);
    }
}
