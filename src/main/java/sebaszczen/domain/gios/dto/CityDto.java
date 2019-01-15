package sebaszczen.domain.gios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityDto {
    @JsonProperty("id")
    private int cityId;
    private String name;
    @JsonProperty("commune")
    private CommuneDto communeDto;

    public int getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public CommuneDto getCommuneDto() {
        return communeDto;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommuneDto(CommuneDto communeDto) {
        this.communeDto = communeDto;
    }
}
