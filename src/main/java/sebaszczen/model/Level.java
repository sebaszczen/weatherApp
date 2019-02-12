package sebaszczen.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Level {
    @Id
    private Long id;
    private int airConditionLevel;
    private String airConditionInWord;
//    @OneToMany(  orphanRemoval = true,mappedBy = "stIndexLevel")
//    private List<AirConditionData>airConditionDataList;
//    @OneToMany( mappedBy = "so2IndexLevel")
//    private List<AirConditionData>airConditionDataList2;
//    @OneToMany( mappedBy = "no2IndexLevel")
//    private List<AirConditionData>airConditionDataList3;
//    @OneToMany( mappedBy = "coIndexLevel")
//    private List<AirConditionData>airConditionDataList4;
//    @OneToMany( mappedBy = "pm10IndexLevel")
//    private List<AirConditionData>airConditionDataList5;
//    @OneToMany( mappedBy = "pm25IndexLevel")
//    private List<AirConditionData>airConditionDataList6;
//    @OneToMany( mappedBy = "o3IndexLevel")
//    private List<AirConditionData>airConditionDataList7;
//    @OneToMany( mappedBy = "c6h6IndexLevel")
//    private List<AirConditionData>airConditionDataList8;

    public Level() {
    }

    public Level(Long id, String airConditionInWord, int airLevelByNumber) {
        this.id=id;
        this.airConditionInWord = airConditionInWord;
        this.airConditionLevel = airLevelByNumber;
    }

    public void setAirConditionInWord(String airConditionInWord) {
        this.airConditionInWord = airConditionInWord;
    }

    public void setAirConditionLevel(int airConditionLevel) {
        this.airConditionLevel = airConditionLevel;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Level)) return false;
        Level level = (Level) o;
        return airConditionLevel == level.airConditionLevel &&
                Objects.equals(airConditionInWord, level.airConditionInWord);
    }

    @Override
    public int hashCode() {

        return Objects.hash(airConditionLevel, airConditionInWord);
    }

    public int getAirConditionLevel() {
        return airConditionLevel;
    }

    public String getAirConditionInWord() {
        return airConditionInWord;
    }
}
