package sebaszczen.model;

import sebaszczen.dto.CityDto;
import sebaszczen.dto.CommuneDto;
import sebaszczen.dto.StationLocalizationDto;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

@Entity
public class StationLocalization {
    @Id
    @GeneratedValue
    private Long id;
    private int stationId;
    private String gegrLat;
    private String gegrLon;
    @Embedded
    private City city;
    private String addressStreet;

    public StationLocalization(StationLocalizationDto stationLocalizationDto) {
        this.stationId = stationLocalizationDto.getStationId();
        this.gegrLat = stationLocalizationDto.getGegrLat();
        this.gegrLon = stationLocalizationDto.getGegrLon();
//        CityDto cityDto = Optional.ofNullable(stationLocalizationDto.getCityDto()).orElse(new CityDto(0,"no data",new CommuneDto()));
//        this.city = new City(cityDto.getCityId(),cityDto.getCityName(),cityDto.getCommuneDto()) ;
        CityDto cityDto = stationLocalizationDto.getCityDto();
        this.city=Optional.ofNullable(cityDto).isPresent()?new City(cityDto):new City();
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
                Objects.equals(gegrLat, that.gegrLat) &&
                Objects.equals(gegrLon, that.gegrLon) &&
                Objects.equals(city, that.city) &&
        Objects.equals(addressStreet, that.addressStreet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stationId, gegrLat, gegrLon, city, addressStreet);
    }
}
