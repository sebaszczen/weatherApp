package sebaszczen.domain.dto.gios;

public class StationLocalizationDto {
    private float id;
    private String stationName;
    private String gegrLat;
    private String gegrLon;
    private CityDto cityDto;
    private String addressStreet;

    public float getId() {
        return id;
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

    public CityDto getCityDto() {
        return cityDto;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setId(float id) {
        this.id = id;
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

    public void setCityDto(CityDto cityDto) {
        this.cityDto = cityDto;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }
}
