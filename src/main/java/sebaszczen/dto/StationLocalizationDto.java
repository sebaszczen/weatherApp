package sebaszczen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sebaszczen.model.airModel.AirMeasurementLocalization;

public class StationLocalizationDto {
    @JsonProperty("id")
    private int stationId;
    private String stationName;
    private String gegrLat;
    private String gegrLon;
    @JsonProperty("city")
    private AirCityDto airCityDto;
    private String addressStreet;

    public int getStationId() {
        return stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public String getGegrLat() {
        return gegrLat;
    }

    public String getGegrLon() {
        return gegrLon;
    }

    public AirCityDto getAirCityDto() {
        return airCityDto;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setGegrLat(String gegrLat) {
        this.gegrLat = gegrLat;
    }

    public void setGegrLon(String gegrLon) {
        this.gegrLon = gegrLon;
    }

    public void setAirCityDto(AirCityDto airCityDto) {
        this.airCityDto = airCityDto;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public AirMeasurementLocalization convertToEntity() {
        return new AirMeasurementLocalization(this);
    }
}
