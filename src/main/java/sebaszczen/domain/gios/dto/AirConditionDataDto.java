package sebaszczen.domain.gios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirConditionDataDto {
    @JsonProperty("id")
    private int stationId;
    private String stCalcDate;
    private LevelDto stIndexLevel; //powietrze ogólnie
    private LevelDto so2IndexLevel; //dwutlenek siarki
    private String so2SourceDataDate;
    private LevelDto no2IndexLevel; //dwutlenek azotu
    private LevelDto coIndexLevel;//tlenek wegla
    private String coSourceDataDate;
    private LevelDto pm10IndexLevel; //pył zawieszony PM10
    private LevelDto pm25IndexLevel;
    private LevelDto o3IndexLevel;
    private LevelDto c6h6IndexLevel;

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setStCalcDate(String stCalcDate) {
        this.stCalcDate = stCalcDate;
    }

    public void setStIndexLevel(LevelDto stIndexLevel) {
        this.stIndexLevel = stIndexLevel;
    }

    public void setSo2IndexLevel(LevelDto so2IndexLevel) {
        this.so2IndexLevel = so2IndexLevel;
    }

    public void setSo2SourceDataDate(String so2SourceDataDate) {
        this.so2SourceDataDate = so2SourceDataDate;
    }

    public void setNo2IndexLevel(LevelDto no2IndexLevel) {
        this.no2IndexLevel = no2IndexLevel;
    }

    public void setCoIndexLevel(LevelDto coIndexLevel) {
        this.coIndexLevel = coIndexLevel;
    }

    public void setCoSourceDataDate(String coSourceDataDate) {
        this.coSourceDataDate = coSourceDataDate;
    }

    public void setPm10IndexLevel(LevelDto pm10IndexLevel) {
        this.pm10IndexLevel = pm10IndexLevel;
    }

    public void setPm25IndexLevel(LevelDto pm25IndexLevel) {
        this.pm25IndexLevel = pm25IndexLevel;
    }

    public void setO3IndexLevel(LevelDto o3IndexLevel) {
        this.o3IndexLevel = o3IndexLevel;
    }

    public void setC6h6IndexLevel(LevelDto c6h6IndexLevel) {
        this.c6h6IndexLevel = c6h6IndexLevel;
    }
}
