package sebaszczen.model.airModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import sebaszczen.dto.AirDataDto;
import sebaszczen.dto.AirQualityDto;
import sebaszczen.model.City;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AirData {
    @Id
    @GeneratedValue
    private Long id;
    private int stationId;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime stCalcDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality stIndexAirQuality; //powietrze ogólnie
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality so2IndexAirQuality; //dwutlenek siarki
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality no2IndexAirQuality; //dwutlenek azotu
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality coIndexAirQuality;//tlenek wegla
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality pm10IndexAirQuality; //pył zawieszony PM10
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality pm25IndexAirQuality;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality c6H6IndexAirQuality;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality o3IndexAirQuality;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirMeasurementLocalization airMeasurementLocalization;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    public AirData() {
    }

    public AirData(AirDataDto airDataDto) {
        this.stationId = airDataDto.getStationId();
        this.stCalcDate = airDataDto.getStCalcDate();
        AirQualityDto stIndexLevel = airDataDto.getStIndexLevel();
        this.stIndexAirQuality = stIndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) stIndexLevel.getAirLevelByNumber(), stIndexLevel.getAirConditionInWord(), stIndexLevel.getAirLevelByNumber());
        AirQualityDto so2IndexLevel = airDataDto.getSo2IndexLevel();
        this.so2IndexAirQuality = so2IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) so2IndexLevel.getAirLevelByNumber(), so2IndexLevel.getAirConditionInWord(), so2IndexLevel.getAirLevelByNumber());
        AirQualityDto no2IndexLevel = airDataDto.getNo2IndexLevel();
        this.no2IndexAirQuality = no2IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) no2IndexLevel.getAirLevelByNumber(), no2IndexLevel.getAirConditionInWord(), no2IndexLevel.getAirLevelByNumber());
        AirQualityDto coIndexLevel = airDataDto.getCoIndexLevel();
        this.coIndexAirQuality = coIndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) coIndexLevel.getAirLevelByNumber(), coIndexLevel.getAirConditionInWord(), coIndexLevel.getAirLevelByNumber());
        AirQualityDto pm10IndexLevel = airDataDto.getPm10IndexLevel();
        this.pm10IndexAirQuality = pm10IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) pm10IndexLevel.getAirLevelByNumber(), pm10IndexLevel.getAirConditionInWord(), pm10IndexLevel.getAirLevelByNumber());
        AirQualityDto pm25IndexLevel = airDataDto.getPm25IndexLevel();
        this.pm25IndexAirQuality = pm25IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) pm25IndexLevel.getAirLevelByNumber(), pm25IndexLevel.getAirConditionInWord(), pm25IndexLevel.getAirLevelByNumber());
        AirQualityDto c6h6IndexLevel = airDataDto.getC6h6IndexLevel();
        this.c6H6IndexAirQuality = c6h6IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) c6h6IndexLevel.getAirLevelByNumber(), c6h6IndexLevel.getAirConditionInWord(), c6h6IndexLevel.getAirLevelByNumber());
        AirQualityDto o3IndexLevel = airDataDto.getO3IndexLevel();
        this.o3IndexAirQuality = o3IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) o3IndexLevel.getAirLevelByNumber(), o3IndexLevel.getAirConditionInWord(), o3IndexLevel.getAirLevelByNumber());
    }

    @JsonIgnore
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setAirMeasurementLocalization(AirMeasurementLocalization airMeasurementLocalization) {
        this.airMeasurementLocalization = airMeasurementLocalization;
    }

    public AirMeasurementLocalization getAirMeasurementLocalization() {
        return airMeasurementLocalization;
    }

    public LocalDateTime getStCalcDate() {
        return stCalcDate;
    }

    @JsonIgnore
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

    public AirQuality getC6H6IndexAirQuality() {
        return c6H6IndexAirQuality;
    }

    public AirQuality getO3IndexAirQuality() {
        return o3IndexAirQuality;
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

