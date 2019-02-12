package sebaszczen.model.airModel;

import sebaszczen.dto.CityDto;
import sebaszczen.dto.CommuneDto;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class AirMeasurementCity {
    private String city;
    @Embedded
    private AirMeasurementCommune airMeasurementCommune;

    public AirMeasurementCity(CityDto cityDto) {
        this.city = cityDto.getCityName();
        CommuneDto communeDto = cityDto.getCommuneDto();
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
