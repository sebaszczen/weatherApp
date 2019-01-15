package sebaszczen.domain.gios;

public class AirConditionData {
    private int stationId;
    private String stCalcDate;
    private Level stIndexLevel; //powietrze ogólnie
    private Level so2IndexLevel; //dwutlenek siarki
    private String so2SourceDataDate;
    private Level no2IndexLevel; //dwutlenek azotu
    private Level coIndexLevel;//tlenek wegla
    private String coSourceDataDate;
    private Level pm10IndexLevel; //pył zawieszony PM10
    private Level pm25IndexLevel;
    private Level o3IndexLevel;
    private Level c6h6IndexLevel;

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setStCalcDate(String stCalcDate) {
        this.stCalcDate = stCalcDate;
    }

    public void setStIndexLevel(Level stIndexLevel) {
        this.stIndexLevel = stIndexLevel;
    }

    public void setSo2IndexLevel(Level so2IndexLevel) {
        this.so2IndexLevel = so2IndexLevel;
    }

    public void setSo2SourceDataDate(String so2SourceDataDate) {
        this.so2SourceDataDate = so2SourceDataDate;
    }

    public void setNo2IndexLevel(Level no2IndexLevel) {
        this.no2IndexLevel = no2IndexLevel;
    }

    public void setCoIndexLevel(Level coIndexLevel) {
        this.coIndexLevel = coIndexLevel;
    }

    public void setCoSourceDataDate(String coSourceDataDate) {
        this.coSourceDataDate = coSourceDataDate;
    }

    public void setPm10IndexLevel(Level pm10IndexLevel) {
        this.pm10IndexLevel = pm10IndexLevel;
    }

    public void setPm25IndexLevel(Level pm25IndexLevel) {
        this.pm25IndexLevel = pm25IndexLevel;
    }

    public void setO3IndexLevel(Level o3IndexLevel) {
        this.o3IndexLevel = o3IndexLevel;
    }

    public void setC6h6IndexLevel(Level c6h6IndexLevel) {
        this.c6h6IndexLevel = c6h6IndexLevel;
    }
}
