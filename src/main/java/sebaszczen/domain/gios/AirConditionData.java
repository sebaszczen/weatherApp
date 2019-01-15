package sebaszczen.domain.gios;

import sebaszczen.domain.gios.dto.AirConditionDataDto;
import sebaszczen.domain.gios.dto.LevelDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Optional;

@Entity
public class AirConditionData {
    @Id
    @GeneratedValue
    private Long id;
    private int stationId;
    private String stCalcDate;
    @OneToOne
    private Level stIndexLevel; //powietrze ogólnie
    @OneToOne
    private Level so2IndexLevel; //dwutlenek siarki
    private String so2SourceDataDate;
    @OneToOne
    private Level no2IndexLevel; //dwutlenek azotu
    @OneToOne
    private Level coIndexLevel;//tlenek wegla
    private String coSourceDataDate;
    @OneToOne
    private Level pm10IndexLevel; //pył zawieszony PM10
    @OneToOne
    private Level pm25IndexLevel;
    @OneToOne
    private Level o3IndexLevel;
    @OneToOne
    private Level c6h6IndexLevel;

    public AirConditionData() {
    }

    public AirConditionData(AirConditionDataDto airConditionDataDto) {
        this.stationId = airConditionDataDto.getStationId();
        this.stCalcDate = airConditionDataDto.getStCalcDate();
        LevelDto stIndexLevel = airConditionDataDto.getStIndexLevel();
        this.stIndexLevel = new Level(Optional.ofNullable(stIndexLevel.getAirConditionInWord()).orElse("brak"));
        LevelDto so2IndexLevel = airConditionDataDto.getSo2IndexLevel();
        this.so2IndexLevel = new Level(Optional.ofNullable(so2IndexLevel).);
        this.so2SourceDataDate = airConditionDataDto.getSo2SourceDataDate();
        LevelDto no2IndexLevel = airConditionDataDto.getNo2IndexLevel();
        this.no2IndexLevel = new Level(Optional.ofNullable(no2IndexLevel.getAirConditionInWord()).orElse("brak"));
        LevelDto coIndexLevel = airConditionDataDto.getCoIndexLevel();
        this.coIndexLevel = new Level(Optional.ofNullable(coIndexLevel.getAirConditionInWord()).orElse("brak"));
        this.coSourceDataDate = airConditionDataDto.getCoSourceDataDate();
        LevelDto pm10IndexLevel = airConditionDataDto.getPm10IndexLevel();
        this.pm10IndexLevel = new Level(Optional.ofNullable(pm10IndexLevel.getAirConditionInWord()).orElse("brak"));
        LevelDto pm25IndexLevel = airConditionDataDto.getPm25IndexLevel();
        this.pm25IndexLevel = new Level(Optional.ofNullable(pm25IndexLevel.getAirConditionInWord()).orElse("brak"));
        LevelDto o3IndexLevel = airConditionDataDto.getO3IndexLevel();
        this.o3IndexLevel = new Level(Optional.ofNullable(o3IndexLevel.getAirConditionInWord()).orElse("brak"));
        LevelDto c6h6IndexLevel = airConditionDataDto.getC6h6IndexLevel();
        this.c6h6IndexLevel = new Level(Optional.ofNullable(c6h6IndexLevel.getAirConditionInWord()).orElse("brak"));
    }
}
