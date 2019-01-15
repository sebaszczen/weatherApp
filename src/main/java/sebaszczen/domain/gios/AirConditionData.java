package sebaszczen.domain.gios;

import sebaszczen.domain.gios.dto.AirConditionDataDto;
import sebaszczen.domain.gios.dto.LevelDto;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class AirConditionData {
    @Id
    @GeneratedValue
    private Long id;
    private int stationId;
    private String stCalcDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Level stIndexLevel; //powietrze ogólnie
    @OneToOne(cascade = CascadeType.ALL)
    private Level so2IndexLevel; //dwutlenek siarki
    private String so2SourceDataDate;
    @OneToOne(cascade = CascadeType.ALL)

    private Level no2IndexLevel; //dwutlenek azotu
    @OneToOne(cascade = CascadeType.ALL)

    private Level coIndexLevel;//tlenek wegla
    private String coSourceDataDate;
    @OneToOne(cascade = CascadeType.ALL)

    private Level pm10IndexLevel; //pył zawieszony PM10
    @OneToOne(cascade = CascadeType.ALL)

    private Level pm25IndexLevel;
    @OneToOne(cascade = CascadeType.ALL)

    private Level o3IndexLevel;
    @OneToOne(cascade = CascadeType.ALL)

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
        this.so2SourceDataDate = airConditionDataDto.getSo2SourceDataDate();
        LevelDto no2IndexLevel = airConditionDataDto.getNo2IndexLevel();
        this.no2IndexLevel = no2IndexLevel == null ? new Level() : new Level(no2IndexLevel.getAirConditionInWord(), no2IndexLevel.getAirLevelByNumber());
        LevelDto coIndexLevel = airConditionDataDto.getCoIndexLevel();
        this.coIndexLevel = coIndexLevel == null ? new Level() : new Level(coIndexLevel.getAirConditionInWord(), coIndexLevel.getAirLevelByNumber());
        this.coSourceDataDate = airConditionDataDto.getCoSourceDataDate();
        LevelDto pm10IndexLevel = airConditionDataDto.getPm10IndexLevel();
        this.pm10IndexLevel = pm10IndexLevel == null ? new Level() : new Level(pm10IndexLevel.getAirConditionInWord(), pm10IndexLevel.getAirLevelByNumber());
        LevelDto pm25IndexLevel = airConditionDataDto.getPm25IndexLevel();
        this.pm25IndexLevel = pm25IndexLevel == null ? new Level() : new Level(pm25IndexLevel.getAirConditionInWord(), pm25IndexLevel.getAirLevelByNumber());
        LevelDto o3IndexLevel = airConditionDataDto.getO3IndexLevel();
        this.o3IndexLevel = o3IndexLevel == null ? new Level() : new Level(o3IndexLevel.getAirConditionInWord(), o3IndexLevel.getAirLevelByNumber());
        LevelDto c6h6IndexLevel = airConditionDataDto.getC6h6IndexLevel();
        this.c6h6IndexLevel = c6h6IndexLevel == null ? new Level() : new Level(c6h6IndexLevel.getAirConditionInWord(), c6h6IndexLevel.getAirLevelByNumber());
    }
}
