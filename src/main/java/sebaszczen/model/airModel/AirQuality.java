package sebaszczen.model.airModel;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AirQuality {
    @Id
    private Long id;
    private int airConditionLevel;
    private String airConditionInWord;

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

    @JsonIgnore
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
