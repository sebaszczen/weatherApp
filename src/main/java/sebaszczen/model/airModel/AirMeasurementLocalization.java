package sebaszczen.model.airModel;

import sebaszczen.dto.AirCityDto;
import sebaszczen.dto.StationLocalizationDto;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity
public class AirMeasurementLocalization {
    @Id
    @GeneratedValue
    private Long id;
    private int stationId;
    private String gegrLat;
    private String gegrLon;
    @Embedded
    private AirMeasurementCity airMeasurementCity;
    private String addressStreet;

    public AirMeasurementLocalization(StationLocalizationDto stationLocalizationDto) {
        this.stationId = stationLocalizationDto.getStationId();
        this.gegrLat = stationLocalizationDto.getGegrLat();
        this.gegrLon = stationLocalizationDto.getGegrLon();
//        AirCityDto airCityDto = Optional.ofNullable(stationLocalizationDto.getAirCityDto()).orElse(new AirCityDto(0,"no data",new CommuneDto()));
//        this.airMeasurementCity = new AirMeasurementCity(airCityDto.getCityId(),airCityDto.getCityName(),airCityDto.getCommuneDto()) ;
        AirCityDto airCityDto = stationLocalizationDto.getAirCityDto();
        this.airMeasurementCity =Optional.ofNullable(airCityDto).isPresent()?new AirMeasurementCity(airCityDto):new AirMeasurementCity();
        this.addressStreet = stationLocalizationDto.getAddressStreet();
    }

    public AirMeasurementLocalization() {
    }

    public int getStationId() {
        return stationId;
    }

    public String getGegrLat() {
        return gegrLat;
    }

    public String getGegrLon() {
        return gegrLon;
    }

    public AirMeasurementCity getAirMeasurementCity() {
        return airMeasurementCity;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirMeasurementLocalization that = (AirMeasurementLocalization) o;
        return stationId == that.stationId &&
                Objects.equals(id, that.id) &&
                Objects.equals(gegrLat, that.gegrLat) &&
                Objects.equals(gegrLon, that.gegrLon) &&
                Objects.equals(airMeasurementCity, that.airMeasurementCity) &&
        Objects.equals(addressStreet, that.addressStreet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stationId, gegrLat, gegrLon, airMeasurementCity, addressStreet);
    }
}
