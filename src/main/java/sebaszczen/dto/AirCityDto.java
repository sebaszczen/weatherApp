package sebaszczen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirCityDto {
    @JsonProperty("id")
    private int cityId;
    @JsonProperty("name")
    private String cityName;
    @JsonProperty("commune")
    private CommuneDto communeDto;

    public AirCityDto(int cityId, String cityName, CommuneDto communeDto) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.communeDto = communeDto;
    }

    public AirCityDto() {
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public CommuneDto getCommuneDto() {
        return communeDto;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCommuneDto(CommuneDto communeDto) {
        this.communeDto = communeDto;
    }
}
