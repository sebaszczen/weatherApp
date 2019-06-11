package sebaszczen.model.airModel;

import sebaszczen.dto.AirCityDto;
import sebaszczen.dto.CommuneDto;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Embeddable
public class AirMeasurementCity {
    private String city;
    @Embedded
    private AirMeasurementCommune airMeasurementCommune;

    public AirMeasurementCity(AirCityDto airCityDto) {
        this.city = airCityDto.getCityName();
        CommuneDto communeDto = airCityDto.getCommuneDto();
        this.airMeasurementCommune = new AirMeasurementCommune(communeDto.getProvinceName());
    }

    public AirMeasurementCity() {
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAirMeasurementCommune(AirMeasurementCommune airMeasurementCommune) {
        this.airMeasurementCommune = airMeasurementCommune;
    }

    public String getCity() {
        return city;
    }

    public AirMeasurementCommune getAirMeasurementCommune() {
        return airMeasurementCommune;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirMeasurementCity airMeasurementCity1 = (AirMeasurementCity) o;
        return Objects.equals(city, airMeasurementCity1.city) &&
                Objects.equals(airMeasurementCommune, airMeasurementCity1.airMeasurementCommune);
    }

    @Override
    public int hashCode() {

        return Objects.hash(city, airMeasurementCommune);
    }
}
