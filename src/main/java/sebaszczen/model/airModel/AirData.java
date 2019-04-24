package sebaszczen.model.airModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import sebaszczen.dto.AirDataDto;
import sebaszczen.dto.AirQualityDto;
import sebaszczen.model.cityModel.City;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AirData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stationId;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime stCalcDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private AirQuality stIndexLevel; //powietrze ogólnie
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality so2IndexLevel; //dwutlenek siarki
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality no2IndexLevel; //dwutlenek azotu
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality coIndexLevel;//tlenek wegla
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality pm10IndexLevel; //pył zawieszony PM10
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality pm25IndexLevel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality c6H6IndexLevel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirQuality o3IndexLevel;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirMeasurementLocalization airMeasurementLocalization;
    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    public AirData() {
    }

    public AirData(AirDataDto airDataDto) {
        this.stationId = airDataDto.getStationId();
        this.stCalcDate = airDataDto.getStCalcDate();
        AirQualityDto stIndexLevel = airDataDto.getStIndexLevel();
        this.stIndexLevel = stIndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) stIndexLevel.getAirLevelByNumber(), stIndexLevel.getAirConditionInWord(), stIndexLevel.getAirLevelByNumber());
        AirQualityDto so2IndexLevel = airDataDto.getSo2IndexLevel();
        this.so2IndexLevel = so2IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) so2IndexLevel.getAirLevelByNumber(), so2IndexLevel.getAirConditionInWord(), so2IndexLevel.getAirLevelByNumber());
        AirQualityDto no2IndexLevel = airDataDto.getNo2IndexLevel();
        this.no2IndexLevel = no2IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) no2IndexLevel.getAirLevelByNumber(), no2IndexLevel.getAirConditionInWord(), no2IndexLevel.getAirLevelByNumber());
        AirQualityDto coIndexLevel = airDataDto.getCoIndexLevel();
        this.coIndexLevel = coIndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) coIndexLevel.getAirLevelByNumber(), coIndexLevel.getAirConditionInWord(), coIndexLevel.getAirLevelByNumber());
        AirQualityDto pm10IndexLevel = airDataDto.getPm10IndexLevel();
        this.pm10IndexLevel = pm10IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) pm10IndexLevel.getAirLevelByNumber(), pm10IndexLevel.getAirConditionInWord(), pm10IndexLevel.getAirLevelByNumber());
        AirQualityDto pm25IndexLevel = airDataDto.getPm25IndexLevel();
        this.pm25IndexLevel = pm25IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) pm25IndexLevel.getAirLevelByNumber(), pm25IndexLevel.getAirConditionInWord(), pm25IndexLevel.getAirLevelByNumber());
        AirQualityDto c6h6IndexLevel = airDataDto.getC6h6IndexLevel();
        this.c6H6IndexLevel = c6h6IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) c6h6IndexLevel.getAirLevelByNumber(), c6h6IndexLevel.getAirConditionInWord(), c6h6IndexLevel.getAirLevelByNumber());
        AirQualityDto o3IndexLevel = airDataDto.getO3IndexLevel();
        this.o3IndexLevel = o3IndexLevel == null ? new AirQuality(-11L, "brak danych", -1) : new AirQuality((long) o3IndexLevel.getAirLevelByNumber(), o3IndexLevel.getAirConditionInWord(), o3IndexLevel.getAirLevelByNumber());
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setStCalcDate(LocalDateTime stCalcDate) {
        this.stCalcDate = stCalcDate;
    }

    public void setStIndexLevel(AirQuality stIndexLevel) {
        this.stIndexLevel = stIndexLevel;
    }

    public void setSo2IndexLevel(AirQuality so2IndexLevel) {
        this.so2IndexLevel = so2IndexLevel;
    }

    public void setNo2IndexLevel(AirQuality no2IndexLevel) {
        this.no2IndexLevel = no2IndexLevel;
    }

    public void setCoIndexLevel(AirQuality coIndexLevel) {
        this.coIndexLevel = coIndexLevel;
    }

    public void setPm10IndexLevel(AirQuality pm10IndexLevel) {
        this.pm10IndexLevel = pm10IndexLevel;
    }

    public void setPm25IndexLevel(AirQuality pm25IndexLevel) {
        this.pm25IndexLevel = pm25IndexLevel;
    }

    public void setC6H6IndexLevel(AirQuality c6H6IndexLevel) {
        this.c6H6IndexLevel = c6H6IndexLevel;
    }

    public void setO3IndexLevel(AirQuality o3IndexLevel) {
        this.o3IndexLevel = o3IndexLevel;
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

    public AirQuality getStIndexLevel() {
        return stIndexLevel;
    }

    public AirQuality getSo2IndexLevel() {
        return so2IndexLevel;
    }

    public AirQuality getNo2IndexLevel() {
        return no2IndexLevel;
    }

    public AirQuality getCoIndexLevel() {
        return coIndexLevel;
    }

    public AirQuality getPm10IndexLevel() {
        return pm10IndexLevel;
    }

    public AirQuality getPm25IndexLevel() {
        return pm25IndexLevel;
    }

    public AirQuality getC6H6IndexLevel() {
        return c6H6IndexLevel;
    }

    public AirQuality getO3IndexLevel() {
        return o3IndexLevel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirData that = (AirData) o;
        return stationId == that.stationId &&
                Objects.equals(id, that.id) &&
                Objects.equals(stCalcDate, that.stCalcDate) &&
                Objects.equals(stIndexLevel, that.stIndexLevel) &&
                Objects.equals(so2IndexLevel, that.so2IndexLevel) &&
                Objects.equals(no2IndexLevel, that.no2IndexLevel) &&
                Objects.equals(coIndexLevel, that.coIndexLevel) &&
                Objects.equals(pm10IndexLevel, that.pm10IndexLevel) &&
                Objects.equals(pm25IndexLevel, that.pm25IndexLevel) &&
                Objects.equals(o3IndexLevel, that.o3IndexLevel) &&
                Objects.equals(c6H6IndexLevel, that.c6H6IndexLevel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stationId, stCalcDate, stIndexLevel, so2IndexLevel, no2IndexLevel, coIndexLevel, pm10IndexLevel, pm25IndexLevel, o3IndexLevel, c6H6IndexLevel);
    }

    @Override
    public String toString() {
        return "AirData{" +
                "id=" + id +
                ", stationId=" + stationId +
                ", stCalcDate=" + stCalcDate +
                ", stIndexLevel=" + stIndexLevel +
                ", so2IndexLevel=" + so2IndexLevel +
                ", no2IndexLevel=" + no2IndexLevel +
                ", coIndexLevel=" + coIndexLevel +
                ", pm10IndexLevel=" + pm10IndexLevel +
                ", pm25IndexLevel=" + pm25IndexLevel +
                ", c6H6IndexLevel=" + c6H6IndexLevel +
                ", o3IndexLevel=" + o3IndexLevel +
                ", airMeasurementLocalization=" + airMeasurementLocalization +
                ", city=" + city +
                '}';
    }
}

