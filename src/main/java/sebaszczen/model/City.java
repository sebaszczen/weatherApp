package sebaszczen.model;

import sebaszczen.dto.CommuneDto;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class City {
    private int cityId;
    private String city;
    @Embedded
    private Commune commune;

    public City(int cityId, String city, CommuneDto communeDto) {
        this.cityId = cityId;
        this.city = city;
        this.commune = new Commune(communeDto.getCommuneName(),communeDto.getDistrictName(),communeDto.getProvinceName());
    }

    public City() {
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return cityId == city1.cityId &&
                Objects.equals(city, city1.city) &&
                Objects.equals(commune, city1.commune);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cityId, city, commune);
    }
}
