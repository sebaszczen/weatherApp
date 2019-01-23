package sebaszczen.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Level {
    @Id
    @GeneratedValue
    private Long id;
    private int airConditionLevel;
    private String airConditionInWord;

    public Level() {
    }

    public Level(String airConditionInWord, int airLevelByNumber) {
        this.airConditionInWord = airConditionInWord;
        this.airConditionLevel = airLevelByNumber;
    }

    public void setAirConditionInWord(String airConditionInWord) {
        this.airConditionInWord = airConditionInWord;
    }

    public void setAirConditionLevel(int airConditionLevel) {
        this.airConditionLevel = airConditionLevel;
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
