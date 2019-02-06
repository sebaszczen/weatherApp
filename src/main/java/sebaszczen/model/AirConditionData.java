package sebaszczen.model;

import sebaszczen.dto.AirConditionDataDto;
import sebaszczen.dto.LevelDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AirConditionData {
    @Id
    @GeneratedValue
    private Long id;
    private int stationId;
    private LocalDateTime stCalcDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="air_level",
            joinColumns={@JoinColumn(name="air_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="level_id", referencedColumnName="id")})
    private Level stIndexLevel; //powietrze ogólnie
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="air_level",
            joinColumns={@JoinColumn(name="air_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="level_id", referencedColumnName="id")})
    private Level so2IndexLevel; //dwutlenek siarki
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="air_level",
            joinColumns={@JoinColumn(name="air_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="level_id", referencedColumnName="id")})
    private Level no2IndexLevel; //dwutlenek azotu
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="air_level",
            joinColumns={@JoinColumn(name="air_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="level_id", referencedColumnName="id")})
    private Level coIndexLevel;//tlenek wegla
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="air_level",
            joinColumns={@JoinColumn(name="air_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="level_id", referencedColumnName="id")})
    private Level pm10IndexLevel; //pył zawieszony PM10
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="air_level",
            joinColumns={@JoinColumn(name="air_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="level_id", referencedColumnName="id")})
    private Level pm25IndexLevel;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="air_level",
            joinColumns={@JoinColumn(name="air_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="level_id", referencedColumnName="id")})
    private Level o3IndexLevel;
    @ManyToOne(cascade = CascadeType.ALL)

    private Level c6h6IndexLevel;

    public AirConditionData() {
    }

    public AirConditionData(AirConditionDataDto airConditionDataDto) {
        this.stationId = airConditionDataDto.getStationId();
        this.stCalcDate = airConditionDataDto.getStCalcDate();
        LevelDto stIndexLevel = airConditionDataDto.getStIndexLevel();
        this.stIndexLevel = stIndexLevel == null ? new Level() : new Level(stIndexLevel.getAirConditionInWord(), stIndexLevel.getAirLevelByNumber());
        LevelDto so2IndexLevel = airConditionDataDto.getSo2IndexLevel();
        this.so2IndexLevel = so2IndexLevel == null ? new Level() : new Level(so2IndexLevel.getAirConditionInWord(), so2IndexLevel.getAirLevelByNumber());
        LevelDto no2IndexLevel = airConditionDataDto.getNo2IndexLevel();
        this.no2IndexLevel = no2IndexLevel == null ? new Level() : new Level(no2IndexLevel.getAirConditionInWord(), no2IndexLevel.getAirLevelByNumber());
        LevelDto coIndexLevel = airConditionDataDto.getCoIndexLevel();
        this.coIndexLevel = coIndexLevel == null ? new Level() : new Level(coIndexLevel.getAirConditionInWord(), coIndexLevel.getAirLevelByNumber());
        LevelDto pm10IndexLevel = airConditionDataDto.getPm10IndexLevel();
        this.pm10IndexLevel = pm10IndexLevel == null ? new Level() : new Level(pm10IndexLevel.getAirConditionInWord(), pm10IndexLevel.getAirLevelByNumber());
        LevelDto pm25IndexLevel = airConditionDataDto.getPm25IndexLevel();
        this.pm25IndexLevel = pm25IndexLevel == null ? new Level() : new Level(pm25IndexLevel.getAirConditionInWord(), pm25IndexLevel.getAirLevelByNumber());
        LevelDto o3IndexLevel = airConditionDataDto.getO3IndexLevel();
        this.o3IndexLevel = o3IndexLevel == null ? new Level() : new Level(o3IndexLevel.getAirConditionInWord(), o3IndexLevel.getAirLevelByNumber());
        LevelDto c6h6IndexLevel = airConditionDataDto.getC6h6IndexLevel();
        this.c6h6IndexLevel = c6h6IndexLevel == null ? new Level() : new Level(c6h6IndexLevel.getAirConditionInWord(), c6h6IndexLevel.getAirLevelByNumber());
    }

    public LocalDateTime getStCalcDate() {
        return stCalcDate;
    }

    public int getStationId() {
        return stationId;
    }

    public Level getStIndexLevel() {
        return stIndexLevel;
    }

    public Level getSo2IndexLevel() {
        return so2IndexLevel;
    }

    public Level getNo2IndexLevel() {
        return no2IndexLevel;
    }

    public Level getCoIndexLevel() {
        return coIndexLevel;
    }

    public Level getPm10IndexLevel() {
        return pm10IndexLevel;
    }

    public Level getPm25IndexLevel() {
        return pm25IndexLevel;
    }

    public Level getO3IndexLevel() {
        return o3IndexLevel;
    }

    public Level getC6h6IndexLevel() {
        return c6h6IndexLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirConditionData that = (AirConditionData) o;
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
                Objects.equals(c6h6IndexLevel, that.c6h6IndexLevel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stationId, stCalcDate, stIndexLevel, so2IndexLevel, no2IndexLevel, coIndexLevel, pm10IndexLevel, pm25IndexLevel, o3IndexLevel, c6h6IndexLevel);
    }
}

