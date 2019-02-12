package sebaszczen.model.airModel;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class AirQuality {
    @Id
    private Long id;
    private int airConditionLevel;
    private String airConditionInWord;
//    @OneToMany(  orphanRemoval = true,mappedBy = "stIndexLevel")
//    private List<AirData>airConditionDataList;
//    @OneToMany( mappedBy = "so2IndexLevel")
//    private List<AirData>airConditionDataList2;
//    @OneToMany( mappedBy = "no2IndexLevel")
//    private List<AirData>airConditionDataList3;
//    @OneToMany( mappedBy = "coIndexLevel")
//    private List<AirData>airConditionDataList4;
//    @OneToMany( mappedBy = "pm10IndexLevel")
//    private List<AirData>airConditionDataList5;
//    @OneToMany( mappedBy = "pm25IndexLevel")
//    private List<AirData>airConditionDataList6;
//    @OneToMany( mappedBy = "o3IndexLevel")
//    private List<AirData>airConditionDataList7;
//    @OneToMany( mappedBy = "c6h6IndexLevel")
//    private List<AirData>airConditionDataList8;

    public AirQuality() {
    }

    public AirQuality(Long id, String airConditionInWord, int airLevelByNumber) {
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
        if (!(o instanceof AirQuality)) return false;
        AirQuality airQuality = (AirQuality) o;
        return airConditionLevel == airQuality.airConditionLevel &&
                Objects.equals(airConditionInWord, airQuality.airConditionInWord);
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
