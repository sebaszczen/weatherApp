package sebaszczen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sebaszczen.model.airModel.AirData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class AirDataDto {
    @JsonProperty("id")
    private int stationId;
    private LocalDateTime stCalcDate;
    private AirQualityDto stIndexLevel; //powietrze ogólnie
    private AirQualityDto so2IndexLevel; //dwutlenek siarki
    private String so2SourceDataDate;
    private AirQualityDto no2IndexLevel; //dwutlenek azotu
    private AirQualityDto coIndexLevel;//tlenek wegla
    private String coSourceDataDate;
    private AirQualityDto pm10IndexLevel; //pył zawieszony PM10
    private AirQualityDto pm25IndexLevel;
    private AirQualityDto o3IndexLevel;
    private AirQualityDto c6h6IndexLevel;

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setStCalcDate(String stCalcDate) {
        String[] split = Optional.ofNullable(stCalcDate).isPresent()?stCalcDate.split(" "):null;
        this.stCalcDate = split==null?null: LocalDateTime.of(LocalDate.parse(split[0]), LocalTime.parse(split[1]));
    }

    public AirData convertToEntity() {
        return new AirData(this);
    }

    public void setStIndexLevel(AirQualityDto stIndexLevel) {
        this.stIndexLevel = stIndexLevel;
    }

    public void setSo2IndexLevel(AirQualityDto so2IndexLevel) {
        this.so2IndexLevel = so2IndexLevel;
    }

    public void setSo2SourceDataDate(String so2SourceDataDate) {
        this.so2SourceDataDate = so2SourceDataDate;
    }

    public void setNo2IndexLevel(AirQualityDto no2IndexLevel) {
        this.no2IndexLevel = no2IndexLevel;
    }

    public void setCoIndexLevel(AirQualityDto coIndexLevel) {
        this.coIndexLevel = coIndexLevel;
    }

    public void setCoSourceDataDate(String coSourceDataDate) {
        this.coSourceDataDate = coSourceDataDate;
    }

    public void setPm10IndexLevel(AirQualityDto pm10IndexLevel) {
        this.pm10IndexLevel = pm10IndexLevel;
    }

    public void setPm25IndexLevel(AirQualityDto pm25IndexLevel) {
        this.pm25IndexLevel = pm25IndexLevel;
    }

    public void setO3IndexLevel(AirQualityDto o3IndexLevel) {
        this.o3IndexLevel = o3IndexLevel;
    }

    public void setC6h6IndexLevel(AirQualityDto c6h6IndexLevel) {
        this.c6h6IndexLevel = c6h6IndexLevel;
    }

    public int getStationId() {
        return stationId;
    }

    public LocalDateTime getStCalcDate() {
        return stCalcDate;
    }

    public AirQualityDto getStIndexLevel() {
        return stIndexLevel;
    }

    public AirQualityDto getSo2IndexLevel() {
        return so2IndexLevel;
    }

    public String getSo2SourceDataDate() {
        return so2SourceDataDate;
    }

    public AirQualityDto getNo2IndexLevel() {
        return no2IndexLevel;
    }

    public AirQualityDto getCoIndexLevel() {
        return coIndexLevel;
    }

    public String getCoSourceDataDate() {
        return coSourceDataDate;
    }

    public AirQualityDto getPm10IndexLevel() {
        return pm10IndexLevel;
    }

    public AirQualityDto getPm25IndexLevel() {
        return pm25IndexLevel;
    }

    public AirQualityDto getO3IndexLevel() {
        return o3IndexLevel;
    }

    public AirQualityDto getC6h6IndexLevel() {
        return c6h6IndexLevel;
    }

}
