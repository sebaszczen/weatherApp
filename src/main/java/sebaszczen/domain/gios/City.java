package sebaszczen.domain.gios;

import sebaszczen.domain.gios.dto.CommuneDto;

import javax.persistence.*;

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
}
