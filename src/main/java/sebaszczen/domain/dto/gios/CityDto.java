package sebaszczen.domain.dto.gios;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityDto {
    @JsonProperty("id")
    private float cityId;
    private String name;
    @JsonProperty("commune")
    private CommuneDto communeDto;

    public void setCityId(float cityId) {
        this.cityId = cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommuneDto(CommuneDto communeDto) {
        this.communeDto = communeDto;
    }
}
