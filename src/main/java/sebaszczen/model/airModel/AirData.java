package sebaszczen.model.airModel;

import sebaszczen.dto.AirConditionDataDto;
import sebaszczen.dto.LevelDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AirData {
    @Id
    @GeneratedValue
    private Long id;
    private int stationId;
    private LocalDateTime stCalcDate;
    @ManyToOne
    private AirQuality stIndexAirQuality; //powietrze ogólnie
    @ManyToOne
    private AirQuality so2IndexAirQuality; //dwutlenek siarki
    @ManyToOne
    private AirQuality no2IndexAirQuality; //dwutlenek azotu
    @ManyToOne
    private AirQuality coIndexAirQuality;//tlenek wegla
    @ManyToOne
    private AirQuality pm10IndexAirQuality; //pył zawieszony PM10
    @ManyToOne
    private AirQuality pm25IndexAirQuality;
    @ManyToOne
    private AirQuality o3IndexAirQuality;
    @ManyToOne
    private AirQuality c6H6IndexAirQuality;
    @OneToOne(cascade = CascadeType.PERSIST)
    private AirMeasurementLocalization airMeasurementLocalization;

    public AirData() {
    }

    public AirData(AirConditionDataDto airConditionDataDto) {
        this.stationId = airConditionDataDto.getStationId();
        this.stCalcDate = airConditionDataDto.getStCalcDate();
        LevelDto stIndexLevel = airConditionDataDto.getStIndexLevel();
        this.stIndexAirQuality = stIndexLevel == null ? new AirQuality(-11L,"brak danych",-1) : new AirQuality((long) stIndexLevel.getAirLevelByNumber(),stIndexLevel.getAirConditionInWord(), stIndexLevel.getAirLevelByNumber());
        LevelDto so2IndexLevel = airConditionDataDto.getSo2IndexLevel();
        this.so2IndexAirQuality = so2IndexLevel == null ? new AirQuality(-11L,"brak danych",-1) : new AirQuality((long) stIndexLevel.getAirLevelByNumber(),so2IndexLevel.getAirConditionInWord(), so2IndexLevel.getAirLevelByNumber());
        LevelDto no2IndexLevel = airConditionDataDto.getNo2IndexLevel();
        this.no2IndexAirQuality = no2IndexLevel == null ? new AirQuality(-11L,"brak danych",-1) : new AirQuality((long) stIndexLevel.getAirLevelByNumber(),no2IndexLevel.getAirConditionInWord(), no2IndexLevel.getAirLevelByNumber());
        LevelDto coIndexLevel = airConditionDataDto.getCoIndexLevel();
        this.coIndexAirQuality = coIndexLevel == null ? new AirQuality(-11L,"brak danych",-1) : new AirQuality((long) stIndexLevel.getAirLevelByNumber(),coIndexLevel.getAirConditionInWord(), coIndexLevel.getAirLevelByNumber());
        LevelDto pm10IndexLevel = airConditionDataDto.getPm10IndexLevel();
        this.pm10IndexAirQuality = pm10IndexLevel == null ? new AirQuality(-11L,"brak danych",-1) : new AirQuality((long) stIndexLevel.getAirLevelByNumber(),pm10IndexLevel.getAirConditionInWord(), pm10IndexLevel.getAirLevelByNumber());
        LevelDto pm25IndexLevel = airConditionDataDto.getPm25IndexLevel();
        this.pm25IndexAirQuality = pm25IndexLevel == null ? new AirQuality(-11L,"brak danych",-1) : new AirQuality((long) stIndexLevel.getAirLevelByNumber(),pm25IndexLevel.getAirConditionInWord(), pm25IndexLevel.getAirLevelByNumber());
        LevelDto o3IndexLevel = airConditionDataDto.getO3IndexLevel();
        this.o3IndexAirQuality = o3IndexLevel == null ? new AirQuality(-11L,"brak danych",-1) : new AirQuality((long) stIndexLevel.getAirLevelByNumber(),o3IndexLevel.getAirConditionInWord(), o3IndexLevel.getAirLevelByNumber());
        LevelDto c6h6IndexLevel = airConditionDataDto.getC6h6IndexLevel();
        this.c6H6IndexAirQuality = c6h6IndexLevel == null ? new AirQuality(-11L,"brak danych",-1) : new AirQuality((long) stIndexLevel.getAirLevelByNumber(),c6h6IndexLevel.getAirConditionInWord(), c6h6IndexLevel.getAirLevelByNumber());
    }

    public void setAirMeasurementLocalization(AirMeasurementLocalization airMeasurementLocalization) {
        this.airMeasurementLocalization = airMeasurementLocalization;
    }

    public LocalDateTime getStCalcDate() {
        return stCalcDate;
    }

    public int getStationId() {
        return stationId;
    }

    public AirQuality getStIndexAirQuality() {
        return stIndexAirQuality;
    }

    public AirQuality getSo2IndexAirQuality() {
        return so2IndexAirQuality;
    }

    public AirQuality getNo2IndexAirQuality() {
        return no2IndexAirQuality;
    }

    public AirQuality getCoIndexAirQuality() {
        return coIndexAirQuality;
    }

    public AirQuality getPm10IndexAirQuality() {
        return pm10IndexAirQuality;
    }

    public AirQuality getPm25IndexAirQuality() {
        return pm25IndexAirQuality;
    }

    public AirQuality getO3IndexAirQuality() {
        return o3IndexAirQuality;
    }

    public AirQuality getC6H6IndexAirQuality() {
        return c6H6IndexAirQuality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirData that = (AirData) o;
        return stationId == that.stationId &&
                Objects.equals(id, that.id) &&
                Objects.equals(stCalcDate, that.stCalcDate) &&
                Objects.equals(stIndexAirQuality, that.stIndexAirQuality) &&
                Objects.equals(so2IndexAirQuality, that.so2IndexAirQuality) &&
                Objects.equals(no2IndexAirQuality, that.no2IndexAirQuality) &&
                Objects.equals(coIndexAirQuality, that.coIndexAirQuality) &&
                Objects.equals(pm10IndexAirQuality, that.pm10IndexAirQuality) &&
                Objects.equals(pm25IndexAirQuality, that.pm25IndexAirQuality) &&
                Objects.equals(o3IndexAirQuality, that.o3IndexAirQuality) &&
                Objects.equals(c6H6IndexAirQuality, that.c6H6IndexAirQuality);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stationId, stCalcDate, stIndexAirQuality, so2IndexAirQuality, no2IndexAirQuality, coIndexAirQuality, pm10IndexAirQuality, pm25IndexAirQuality, o3IndexAirQuality, c6H6IndexAirQuality);
    }
}

